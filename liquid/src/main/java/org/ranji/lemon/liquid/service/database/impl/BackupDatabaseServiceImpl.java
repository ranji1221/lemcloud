package org.ranji.lemon.liquid.service.database.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.liquid.model.database.BackupDatabaseInfo;
import org.ranji.lemon.liquid.model.database.ProgressSingleton;
import org.ranji.lemon.liquid.service.database.prototype.IBackupDatabaseService;
import org.springframework.stereotype.Repository;

@Repository
public class BackupDatabaseServiceImpl extends GenericServiceImpl<BackupDatabaseInfo, Integer>implements IBackupDatabaseService {
	
	private static String hostIP;     //数据库IP地址
	private static String userName;	  //数据库用户名
	private static String password;   //数据库密码
	private static String dbName;     //数据库名
	private static List<String> tables  ; //排除的数据表
		
	@Override
	public void backup(String path) throws IOException{
		initVariableByProperties(); //获取数据库信息
		removeTable();  //获取移除表信息
		Runtime runtime = Runtime.getRuntime();
		//-u后面是用户名，-p是密码-p后面最好不要有空格，-family是数据库的名字
		String cmd = "cmd /c mysqldump --single-transaction -h "+ hostIP +" -u "+ userName +" -p"+ password +" --set-charset=UTF8 "+dbName;
		StringBuffer sbf = new StringBuffer();
		/*for(String s : tables){//排除的表
			sbf.append(" --ignore-table="+dbName+"."+s);
		}*/
		//sbf.append(" --ignore-table=lemon.lemon_liquid_log_systemlog");
		System.out.println(cmd + sbf);
		Process process = runtime.exec(cmd + sbf);
		InputStream inputStream = process.getInputStream();//得到输入流，写成.sql文件
		InputStreamReader reader = new InputStreamReader(inputStream,"utf-8");
		BufferedReader br = new BufferedReader(reader);
		String s = null;
		StringBuffer sb = new StringBuffer();
		while((s = br.readLine()) != null){
			sb.append(s+"\r\n");
		}
		s = sb.toString();
		File file = new File(path);
		file.getParentFile().mkdirs();
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		fileOutputStream.write(s.getBytes());
		fileOutputStream.close();
		br.close();
		reader.close();
		inputStream.close();
	}
	
	@Override
	public void recover(String path,HttpSession session) throws IOException {
		initVariableByProperties();  //初始化数据库连接
		Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec("cmd /c mysql -h "+ hostIP +" -u "+ userName +" -p"+ password +" --default-character-set=utf8 lemon");
		OutputStream outputStream = process.getOutputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
		ProgressSingleton.put(session.getId()+"Size", new File(path).length());   //记录文件大小
		//文件进度长度  
        long progress = 0; 
		String str = null;
		OutputStreamWriter writer = new OutputStreamWriter(outputStream,"utf-8");
		while((str = br.readLine()) != null){
			progress = progress + str.length();  
            //向单例哈希表写入进度  
            ProgressSingleton.put(session.getId()+"Progress", progress);  
			writer.write(str+"\r\n");
		}
		ProgressSingleton.remove(session.getId()+"Size");  
        ProgressSingleton.remove(session.getId()+"Progress");  
		writer.flush();
		outputStream.close();
		br.close();
		writer.close();
		
	}
	/**
	 * 初始化数据库连接信息（通过properties文件获取）
	 * @throws IOException 
	 */
	private void initVariableByProperties() throws IOException{
		//1  通过java.util.ResourceBundle类来读取
		ResourceBundle resource = ResourceBundle.getBundle("config/application");
		userName = resource.getString("spring.datasource.username");
		password = resource.getString("spring.datasource.password");
		String url = resource.getString("spring.datasource.url");
		//获取IP地址
		hostIP = url.split("/")[2].split(":")[0];
	}
	/**
	 * 获取相应数据库备份数据（通过properties文件获取）
	 * @throws IOException 
	 */
	private void removeTable()throws IOException{
		//ResourceBundle resource = ResourceBundle.getBundle("config/database");
		//dbName = resource.getString("database");
		dbName = "lemon";
		//String table = resource.getString("remove.table");
		//tables = Arrays.asList(table.split(",")); 
	}
}

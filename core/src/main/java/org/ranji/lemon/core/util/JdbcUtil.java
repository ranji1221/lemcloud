package org.ranji.lemon.core.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the"License"); 
 * you may not use this file except in compliance with the License.  
 * You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  
 * See the License for the specific language governing permissions and limitations under the License.
 * Copyright [2017] [RanJi] [Email-jiran1221@163.com]
 * 
 * 操作数据库元数据的服务类
 * @author RanJi
 * @date 2013-10-1
 * @since JDK1.7
 * @version 1.0
 */
public class JdbcUtil {
	private static Logger logger = Logger.getLogger(JdbcUtil.class);
	
	private static JdbcUtil jdbcUtil;
	private  Connection con;
	private JdbcUtil(DataBaseType dbType, String hostAddr, String port, String dbName, String userName, String password){
		String driverName = "";
		String jdbcURL = "";
		String dbTypeName = "";
		switch (dbType.getValue()) {
		case 0:
			dbTypeName = "Oracle DataBase";
			driverName = "oracle.jdbc.driver.OracleDriver ";
			jdbcURL = "jdbc:oracle:thin:@"+hostAddr+":"+port;
			if(dbName !=null || !"".equals(dbName)) jdbcURL += ":"+dbName;
			break;
		case 1: 
			dbTypeName = "MySQL DataBase";
			driverName = "com.mysql.jdbc.Driver";
			jdbcURL = "jdbc:mysql://"+hostAddr+":"+port;
			if(dbName !=null || !"".equals(dbName)) jdbcURL += "/"+dbName;
			break;
		case 2:
			dbTypeName = "DB2 DataBase";
			driverName = "com.ibm.db2.jcc.DB2Driver";
			jdbcURL = "jdbc:db2://"+hostAddr+":"+port;
			if(dbName !=null || !"".equals(dbName)) jdbcURL += "/"+dbName;
			break;
		case 3:
			dbTypeName = "SyBase DataBase";
			driverName = "com.sybase.jdbc3.jdbc.SybDriver";
			jdbcURL = "jdbc:sybase:Tds:"+hostAddr+":"+port;
			if(dbName !=null || !"".equals(dbName)) jdbcURL += "/"+dbName;
			break;
		case 4:
			dbTypeName = "SQLServer DataBase";
			driverName = "net.sourceforge.jtds.jdbc.Driver";   //--这里注意，连接MSSQL数据库没有使用官方驱动，而是使用了第三方的驱动
			jdbcURL = "jdbc:jtds:sqlserver://"+hostAddr+":"+port;
			if(dbName !=null || !"".equals(dbName)) jdbcURL += "/"+dbName;
			break;
		}
		
		try{
			Class.forName(driverName);
			con = DriverManager.getConnection(jdbcURL,userName,password);
		}catch(ClassNotFoundException e) {
			logger.info("DataBase Connection Driver Class: " + driverName + " Does Not Exist.");
		}catch(SQLException e){
			logger.info("Sorry,The DataBase Connection is Unsuccessful,Please Check The IP Address、The DataBase Name、UserName And Password Are Correct?");
		}
		logger.info("Congratulations, Connection " + dbTypeName + " is Successful.");
	}
	
	/**
	 * 单例设计模式的应用
	 * @param dbType
	 * @param hostAddr
	 * @param port
	 * @param dbName
	 * @param userName
	 * @param password
	 * @return
	 */
	public static JdbcUtil getInstance(DataBaseType dbType, String hostAddr, String port, String dbName, String userName, String password){
		if(jdbcUtil == null) jdbcUtil = new JdbcUtil(dbType, hostAddr, port, dbName, userName, password);
		return jdbcUtil;
	}
	
	public  Connection getConnection(){
		return con;
	}
	
	/**
	 * 内部使用的释放资源的方法
	 * @param rs
	 * @param sm
	 * @param con
	 */
	private  void close(ResultSet rs, Statement sm, Connection con){
		if(rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				if(sm != null)
					try {
						sm.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally{
						if(con != null)
							try {
								con.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
					}
			}
	}
	
	/**
	 * 获取有几个数据库（名称）
	 * @param con
	 * @return
	 */
	public  List<String> getDataBases(){
		List<String> dataBases = new ArrayList<String>();
		ResultSet rs = null;
		if(con != null){
			try {
				DatabaseMetaData dbMetaData = con.getMetaData();
				rs = dbMetaData.getCatalogs();
				while(rs.next()){
					dataBases.add(rs.getString(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				close(rs,null,null);
			}
		}
		return dataBases;
	}
	
	public  List<String> getTables(String dataBaseName){
		List<String> tables = new ArrayList<String>();
		ResultSet rs = null;
		if(con != null){
			try {
				DatabaseMetaData dbMetaData = con.getMetaData();
				rs = dbMetaData.getTables(dataBaseName, null, null, new String[]{"TABLE","VIEW"});
				while(rs.next()){
					tables.add(rs.getString("TABLE_NAME"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				close(rs,null,null);
			}
		}
		return tables;
	}
	
	public  List<String> getColumns(String tableName,String dataBase){
		List<String> columns = new ArrayList<String>();
		ResultSet rs = null;
		if(con != null){
			try {
				DatabaseMetaData dbMetaData = con.getMetaData();
				rs = dbMetaData.getColumns(dataBase, null, tableName, null);
				while(rs.next()){
					columns.add(rs.getString("COLUMN_NAME")+":" + rs.getString("TYPE_NAME") + ":" +rs.getString("COLUMN_SIZE"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				close(rs,null,null);
			}
		}
		return columns;
	}
	
	/**
	 * 创建数据库
	 * @param dbName 数据库名称
	 */
	public  void createDB(String dbName){
		String sql = "CREATE DATABASE "+dbName;
		Statement stmt = null;
		if(con != null) {
			try {
				stmt = con.createStatement();
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				logger.info("Sorry，DataBase `"+dbName+"` Already Exists.");
				return;
			} finally{
				close(null,stmt,null);
			}
		}
		logger.info("Congratulations, Creatae DataBase `"+dbName+"` Is Successful.");
	}
	
	/**
	 * 执行SQL语句的方法
	 * @return
	 */
	public  void excuteSQL(String sql,String dbName){
		Statement stmt = null;
		if(con != null){
			try {
				con.setCatalog(dbName);
			} catch (SQLException e) {
				logger.info("Sorry，DataBase `"+dbName+"` Does Not Exists,Please Check And Given The Existence Of The DataBase.");
				return;
			}
			try {
				stmt = con.createStatement();
				stmt.executeUpdate(sql);
				logger.info("Excute SQL Statement: `"+sql+"` Is Successful.");
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				close(null,stmt,null);
			}
		}
	}
	
	public  String getJSONStrDataBase(String dbName){
		return getJSONStrDataBase(dbName, "name", "tables", "columns");
	}
	
	public  String getJSONStrDataBase(String dbName,String CommonNodeNameKey,String TablesChildrenNodeNamekey,String columnsChildrenNodeNameKey){
		StringBuilder dbStr = new StringBuilder();
		StringBuilder tableStr = new StringBuilder();
		StringBuilder columnStr = new StringBuilder();
		
		ResultSet tableRS = null;
		ResultSet columnRS = null;
		
		boolean tableFlag = false;
		boolean columnFlag = false;
		
		if(con != null){
			try {
				DatabaseMetaData dbMetaData = con.getMetaData();
				dbStr.append("{\""+CommonNodeNameKey+"\":");
				dbStr.append("\""+dbName+"\",\""+TablesChildrenNodeNamekey+"\":[");
				
				tableRS = dbMetaData.getTables(dbName, null, null, new String[]{"TABLE","VIEW"});
				while(tableRS.next()){
					tableFlag = true;
					tableStr.append("{\""+CommonNodeNameKey+"\":");
					tableStr.append("\""+tableRS.getString("TABLE_NAME")+"\",\""+columnsChildrenNodeNameKey+"\":[");
				
					columnRS = dbMetaData.getColumns(dbName, null, tableRS.getString("TABLE_NAME"), null);
					while(columnRS.next()){
						columnFlag = true;
						columnStr.append("{\""+CommonNodeNameKey+"\":");
						columnStr.append("\""+columnRS.getString("COLUMN_NAME")+"\""+",");
						columnStr.append("\"type\":");
						columnStr.append("\""+columnRS.getString("TYPE_NAME")+"\""+",");
						columnStr.append("\"size\":");
						columnStr.append(columnRS.getString("COLUMN_SIZE"));
						columnStr.append("},");
					}
					if(columnFlag){
						columnFlag = false;
						columnStr = columnStr.replace(columnStr.length()-1, columnStr.length(), "");
						tableStr.append(columnStr);
						columnStr = new StringBuilder();
					}
					tableStr.append("]");
					tableStr.append("},");
				}
				dbStr.append(tableStr);
				if(tableFlag)
					dbStr = dbStr.replace(dbStr.length()-1, dbStr.length(), "");
				dbStr.append("]}");	
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				close(columnRS,null,null);
				close(tableRS,null,null);
			}
		}
		return dbStr.toString();
	}
	
	
	
	/**
	 * 外部使用的释放资源的方法 (最后被调用的方法,以便释放资源)
	 */
	public  void close(){
		if(con != null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
}

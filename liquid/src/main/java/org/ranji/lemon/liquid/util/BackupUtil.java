package org.ranji.lemon.liquid.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 备份文件工具类 backupUtil
 * @author fengjie
 * @date 2017-10-19
 */
public class BackupUtil {

	/**
	 * 获取备份文件夹的绝对路径，用于备份文件（E:/xxx/upload/content）
	 * @param databaseName 数据库名称（如：lemon等）
	 * @param session http会话
	 * @return 路径String
	 */
	public static String getAbsolutePath(String databaseName, HttpSession session) {
		return session.getServletContext().getRealPath("/") + "backup/" + databaseName;
	}
	
	/**
	 * 获取文件备份相对于项目的路径，用于存储到数据库，和页面读取展示文件
	 * @param databaseName 数据库名称（如：lemon等）
	 * @param session http会话
	 * @return 路径String
	 */
	public static String getRelativePath(String databaseName, HttpSession session) {
		return "backup/" + databaseName;
	}
	
	/**
	 * 自定义方式重命名sql文件
	 * @param fileName 原文件名
	 * @return 新文件名（当前时间+4位随机数+扩展名：201703091011581104.sql）
	 */
	public static String rename(String type) {
		// 获取4位随机数字符串
		@SuppressWarnings("deprecation")
		String ranStr = RandomStringUtils.random(4, false, true);
		// 当前时间字符串
		String timeStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); 
		return timeStr + ranStr + "." + type;
	}

}

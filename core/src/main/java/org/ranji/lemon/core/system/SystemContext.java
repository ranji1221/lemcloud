package org.ranji.lemon.core.system;

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
 * 系统常量类
 * @author RanJi
 * @date 2013-10-1
 * @since JDK1.7
 * @version 1.0
 */
public class SystemContext {

	private static ThreadLocal<Integer> offset = new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> authStatus = new ThreadLocal<Integer>();  //-- 为记录日志信息的授权状态而设置
	

	/**
	 * 获取偏移量
	 */
	public static int getOffset() {
		Integer os = (Integer) offset.get();
		if (os == null)
			return 0;
		return os.intValue();
	}

	public static void setOffset(int offsetValue) {
		offset.set(offsetValue);
	}

	public static void removeOffset() {
		offset.remove();
	}

	/**
	 * 获取页大小
	 */
	public static int getPageSize() {
		Integer ps = pageSize.get();
		if (ps == null)
			return Integer.MAX_VALUE;
		return ps.intValue();
	}

	public static void setPageSize(int pageSizeValue) {
		pageSize.set(pageSizeValue);
	}

	public static void removePageSize() {
		pageSize.remove();
	}
	
	/**
	 * 设置授权的状态信息
	 */
	public static int getAuthStatus() {
		Integer os = (Integer) authStatus.get();
		if (os == null)
			return 1;
		return os.intValue();
	}

	public static void setAuthStatus(int authStatusValue) {
		authStatus.set(authStatusValue);
	}

	public static void removeAuthStatus() {
		authStatus.remove();
	}
}

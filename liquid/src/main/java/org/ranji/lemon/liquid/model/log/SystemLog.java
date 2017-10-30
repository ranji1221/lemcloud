package org.ranji.lemon.liquid.model.log;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.ranji.lemon.core.model.AbstractModel;

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
 * 系统日志实体类--记录用户操作行为
 * @author RanJi
 * @date 2017-9-14
 * @since JDK1.7
 * @version 1.0
 */
public class SystemLog extends AbstractModel {
	private static final long serialVersionUID = -8290370426605089254L;
	
	private int id;
	private String logType;				//-- 日志类型      1:info  2:error
	private String logTitle;			//-- 日志标题      2: 资源 xxxx模块--xxxx操作 
	private String remoteAddr;			//-- 请求地址     3: IP地址
	private String requestUri;			//-- 请求URI 4: URI    localhost/user/add
	private String method;				//-- 请求方式     4: POST/GET/PUT
	private String params;				//-- 提交参数    5: userName=zhangsan&userPass=123....
	private String exception;			//-- 异常            6: 异常信息  
	private int authStatus = 1;				//-- 权限状态   1: 代表不需权限   2：无权限   3：享有权限
	

	private Date operateDate;			//-- 开始时间    开发访问资源时间
	private String timeout;				//-- 耗时      多少毫秒
	
	private String userName;			//-- 用户			
	//private String logId;               //--日志记录id
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogType() {
		return StringUtils.isBlank(logType) ? logType : logType.trim();
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	//-- 做去除字符串两边空格的处理，以下的属性做类似的处理
	public String getLogTitle() {
		return StringUtils.isBlank(logTitle) ? logTitle : logTitle.trim();
	}
	public void setLogTitle(String logTitle) {
		this.logTitle = logTitle;
	}
	public String getRemoteAddr() {
		return StringUtils.isBlank(remoteAddr) ? remoteAddr : remoteAddr.trim();
	}
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	public String getRequestUri() {
		return StringUtils.isBlank(requestUri) ?requestUri : requestUri.trim();
	}
	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}
	public String getMethod() {
		return StringUtils.isBlank(method) ? method : method.trim();
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getParams() {
		return StringUtils.isBlank(params) ?params : params.trim();
	}
	public void setParams(String params) {
		this.params = params;
	}
	/**
	 * 设置请求参数 (待重写)
	 * @param paramMap
	 */
	public void setMapToParams(Map<String,String[]> paramMap){
		if(paramMap == null) return;
		StringBuilder params = new StringBuilder();
		for(Map.Entry<String,String[]> param : ((Map<String, String[]>)paramMap).entrySet()){
			params.append(( "".equals(params.toString() )  ?  "" : "&") + param.getKey() + "=");
			String paramValue = (param.getValue() != null && param.getValue().length>0 ? param.getValue()[0] : "");
			params.append( StringUtils.abbreviate(StringUtils.endsWithIgnoreCase(param.getKey(), "password" ) ? "" : paramValue, 100));
		}
		this.params = params.toString();
	}
	
	public String getException() {
		return StringUtils.isBlank(exception) ? exception : exception.trim();
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public Date getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
	public String getTimeout() {
		return timeout;
	}
	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}
	
	public int getAuthStatus() {
		return authStatus;
	}
	public void setAuthStatus(int authStatus) {
		this.authStatus = authStatus;
	}
	
	@Override
	public String toString(){
		return "类型：" + logType + "\n标题：" + logTitle + "\n访问地址：" + remoteAddr + 
				"\n请求地址："+requestUri + "\n请求参数："+params +
				"\n授权状态：" + authStatus + "\n开始访问时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(operateDate) +
				"\n耗时：" + timeout + "\n访问者：" + userName;
	}
}

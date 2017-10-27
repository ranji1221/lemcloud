package org.ranji.lemon.jersey.model.oauth2;

import org.ranji.lemon.core.model.AbstractModel;
import org.ranji.lemon.core.util.JsonUtil;

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
 * Oauth2认证体系的令牌实体类
 * @author RanJi
 * @date 2017-10-9
 * @since JDK1.7
 * @version 1.0
 */
public class AccessToken extends AbstractModel{
	private static final long serialVersionUID = -1806337753394935347L;
	
	private String token;
	private String tokenExpiredSeconds;
	private String username;
	private String clientId;
	private String tokenType;
	private String refreshToken;
	private String refreshTokenExpiredSeconds;
	
	public AccessToken(){
		
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getTokenExpiredSeconds() {
		return tokenExpiredSeconds;
	}
	public void setTokenExpiredSeconds(String tokenExpiredSeconds) {
		this.tokenExpiredSeconds = tokenExpiredSeconds;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getRefreshTokenExpiredSeconds() {
		return refreshTokenExpiredSeconds;
	}
	public void setRefreshTokenExpiredSeconds(String refreshTokenExpiredSeconds) {
		this.refreshTokenExpiredSeconds = refreshTokenExpiredSeconds;
	}
	
	@Override
	public String toString() {
		return JsonUtil.objectToJson(this);
	}
}

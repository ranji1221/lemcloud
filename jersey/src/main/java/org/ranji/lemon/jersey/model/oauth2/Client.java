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
 * Oauth2认证体系的客户端实体类
 * @author RanJi
 * @date 2017-10-9
 * @since JDK1.7
 * @version 1.0
 */
public class Client extends AbstractModel{
	private static final long serialVersionUID = -8957828394887997447L;
	
	private String clientName;			//-- 客户端名称
	private String clientId;			//-- 客户端ID
	private String clientSecret;		//-- 客户端安全key
	private String clientUri;
	private String clientIconUri;
	private String scope;
	private String grantType;
	private String redirectUri;
	private String description;
	
	public Client(){}
	
	public String getClientUri() {
		return clientUri;
	}
	public void setClientUri(String clientUri) {
		this.clientUri = clientUri;
	}
	public String getClientIconUri() {
		return clientIconUri;
	}
	public void setClientIconUri(String clientIconUri) {
		this.clientIconUri = clientIconUri;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getGrantType() {
		return grantType;
	}
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
	public String getRedirectUri() {
		return redirectUri;
	}
	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	
	@Override
	public String toString() {
		return JsonUtil.objectToJson(this);
	}
}


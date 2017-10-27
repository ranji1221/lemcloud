package org.ranji.lemon.jersey.service.oauth2.prototype;
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
 * Oauth2认证体系的业务服务接口
 * @author RanJi
 * @date 2017-10-9
 * @since JDK1.7
 * @version 1.0
 */
public interface IOauthService {
	/**
	 * 某第三方应用产生ClientId和ClientSecret的业务方法
	 * @param thirdAppName 
	 * @return 返回ClientId和ClientSecret的JSON字符串
	 */
	public String generateClientIdAndClientSecret(String thirdAppName);
	/**
	 * 检查某第三方应用的ClientId是否正确
	 * @param clientId
	 * @return
	 */
	public boolean checkClientId(String clientId);
	/**
	 * 检查某第三方应用的ClientSecret是否正确
	 * @param clientSecret
	 * @return
	 */
	public boolean checkClientSecret(String clientSecret);
	
}

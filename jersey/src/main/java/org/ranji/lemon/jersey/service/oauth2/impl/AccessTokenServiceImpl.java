package org.ranji.lemon.jersey.service.oauth2.impl;


import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.jersey.model.oauth2.AccessToken;
import org.ranji.lemon.jersey.service.oauth2.prototype.IAccessTokenService;
import org.springframework.stereotype.Service;

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
 * Oauth2认证体系的令牌业务服务类
 * @author fengjie
 * @date 2017-10-17
 * @since JDK1.7
 * @version 1.0
 */

@Service
public class AccessTokenServiceImpl extends GenericServiceImpl<AccessToken, Integer> implements IAccessTokenService {
	
}


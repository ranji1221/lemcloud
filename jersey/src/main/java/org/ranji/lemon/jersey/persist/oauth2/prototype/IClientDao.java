package org.ranji.lemon.jersey.persist.oauth2.prototype;

import org.ranji.lemon.core.persist.prototype.IGenericDao;
import org.ranji.lemon.jersey.model.oauth2.Client;




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
 * Oauth2认证体系的客户端Dao接口
 * @author RanJi
 * @date 2017-10-9
 * @since JDK1.7
 * @version 1.0
 */

public interface IClientDao extends IGenericDao<Client, Integer>{
	public Client findByClientId(String clientId);
	public Client findByClientSecret(String clientSecret);
}

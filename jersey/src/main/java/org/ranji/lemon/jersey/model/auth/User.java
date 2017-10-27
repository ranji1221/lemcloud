package org.ranji.lemon.jersey.model.auth;

import org.apache.ibatis.type.Alias;
import org.ranji.lemon.core.model.AbstractModel;
import org.ranji.lemon.core.util.JsonUtil;


/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to you under the Apache License, Version 2.0
 * (the"License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License. Copyright [2017] [RanJi] [Email-jiran1221@163.com]
 * 
 * auth权限模块的User类
 * 
 * @author RanJi
 * @date 2017-10-13
 * @since JDK1.7
 * @version 1.0
 */
@Alias("JerseyUser")    //-- 为了避免引入liquid项目中也有User的别名，而引发mybatis别名设置的冲突
public class User extends AbstractModel {
	private static final long serialVersionUID = 2048080165498963074L;

	private String username;
	private String password;

	public User() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return JsonUtil.objectToJson(this);
	}

}

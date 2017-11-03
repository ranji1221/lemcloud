package org.ranji.lemon.liquid.security;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.ranji.lemon.liquid.model.authority.User;
import org.ranji.lemon.liquid.service.authority.prototype.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

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
 * Shiro框架实现类
 * @author RanJi
 * @date 2013-10-1
 * @since JDK1.7
 * @version 1.0
 */
public class SystemRealm extends AuthorizingRealm{
	
	@Autowired
	@Lazy	//-- 为解决shiro和redis框架的冲突而添加
	private IUserService userService;
	
	/**
	 * 编写认证代码
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authToken) throws AuthenticationException {
		//-- 1. 基于userName和password的令牌
		UsernamePasswordToken token = (UsernamePasswordToken)authToken;
		//-- 2. 根据验证单的填写的名字从后台查找用户
		User user = userService.findByUserName(token.getUsername());
		//-- 3. 返回认证材料信息
		AuthenticationInfo authenInfo = null;
		if(user != null)
			authenInfo = new SimpleAuthenticationInfo(user.getUserName(),user.getUserPass(),getName());
		return authenInfo;
	}
	
	/**
	 * 编写授权代码
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//-- 编写授权代码
		//-- 以下的代码是测试代码，假设所有的用户都会有"user:list"的权限
		//-- 在实际的开发中，我们会自己写好user-role-permission模块，然后从数据库中查询，用户的权限
		//-- 并可以赋予用户权限
		String userName = (String)principals.fromRealm(getName()).iterator().next();
		SimpleAuthorizationInfo info = null;
		if(userName != null && !"".equals(userName))
			info = new SimpleAuthorizationInfo();
		if(userName.equals("zhangsan2")){
			info.addStringPermission("user:add");
			info.addStringPermission("user:list");
			info.addStringPermission("user:bulkadd");
			info.addStringPermission("user:lookuser");
			info.addStringPermission("user:edituser");
			info.addStringPermission("user:authuser");
			//info.addStringPermission("user:delete");
		}
		return info;
		
	}
	
}

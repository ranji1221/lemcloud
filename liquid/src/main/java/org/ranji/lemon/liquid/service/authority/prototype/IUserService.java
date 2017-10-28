package org.ranji.lemon.liquid.service.authority.prototype;

import java.util.List;

import org.ranji.lemon.core.service.prototype.IGenericService;
import org.ranji.lemon.liquid.model.authority.Role;
import org.ranji.lemon.liquid.model.authority.User;

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
 * Authority模块中的IUserService接口类
 * @author RanJi
 * @date 2013-10-1
 * @since JDK1.7
 * @version 1.0
 */
public interface IUserService extends IGenericService<User, Integer> {

	/**
	 * 根据用户名查找用户
	 * @param userName 用户名
	 * @return 用户
	 */
	public User findByUserName(String userName);

	/**
	 * 用户验证
	 * @param userName 用户名
	 * @param userPass 密码
	 * @return 成功后返回用户，不成功返回null
	 */
	public User validate(String userName, String userPass);

	// 用户角色方法

	/**
	 * 存储用户-角色的对应
	 * @param userId 用户id
	 * @param roleId 角色id
	 */
	public void saveUserAndRoleRelation(int userId, int roleId);

	/**
	 * 删除用户-角色的对应
	 * @param userId 用户id
	 * @param roleId 角色id
	 */
	public void deleteUserAndRoleRelation(int userId, int roleId);
	
	/**
	 * 删除某用户的全部用户-角色的对应
	 * @param userId 用户id
	 */
	public void deleteUserAndRolesByUserId(int userId);

	/**
	 * 根据用户id查询全部的用户-角色对应
	 * @param userId 用户id
	 * @return 角色id集合
	 */
	public List<Integer> findUserAndRolesByUserId(int userId);
	
	public List<Role> findRoleByUserId(int userId);

}

package org.ranji.lemon.liquid.persist.authority.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.liquid.model.authority.Role;
import org.ranji.lemon.liquid.model.authority.User;
import org.ranji.lemon.liquid.persist.authority.prototype.IUserDao;
import org.springframework.stereotype.Repository;
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
 * Authority模块中的UserDao实现类
 * @author RanJi
 * @date 2013-10-1
 * @since JDK1.7
 * @version 1.0
 */

@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements IUserDao {

	@Override
	public void saveUserAndRoleRelation(int userId, int roleId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("roleId", roleId);
		sqlSessionTemplate.insert(typeNameSpace + ".saveUserAndRoleRelation", params);
	}

	@Override
	public void deleteUserAndRoleRelation(int userId, int roleId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("roleId", roleId);
		sqlSessionTemplate.delete(typeNameSpace + ".deleteUserAndRoleRelation", params);
	}

	@Override
	public void deleteUserAndRolesRelationByUserId(int userId) {
		sqlSessionTemplate.delete(typeNameSpace + ".deleteUserAndRolesRelationByUserId", userId);
	}

	@Override
	public List<Integer> findUserRolesRelationByUserId(int userId) {
		return sqlSessionTemplate.selectList(typeNameSpace + ".findUserRolesRelationByUserId", userId);
	}
	
	@Override
	public User findUserByUserName(String UserName){
		return sqlSessionTemplate.selectOne(typeNameSpace + ".findUserByUserName", UserName);
	}
	
	@Override
	public List<Role> findRoleByUserId(int userId){
		return sqlSessionTemplate.selectList(typeNameSpace + ".findRoleByUserId", userId);
	}

}

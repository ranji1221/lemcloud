package org.ranji.lemon.jersey.service.auth.impl;

import java.util.List;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.jersey.model.auth.Role;
import org.ranji.lemon.jersey.persist.auth.prototype.IRoleDao;
import org.ranji.lemon.jersey.service.auth.prototype.IRoleService;
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
 * RoleService实现类
 * @author fengjie
 * @date 2017-10-17
 * @since JDK1.7
 * @version 1.0
 */
@Service("JerseyRoleServiceImpl") //-- 为解决其他项目中也有项目的类名，则利用@Autowired自动注入冲突的问题
public class RoleServiceImpl extends GenericServiceImpl<Role, Integer> implements IRoleService {
	

	@Override
	public void saveRoleAndPermissionRelation(int roleId, String permission) {
		((IRoleDao) dao).saveRoleAndPermissionRelation(roleId, permission);
	}

	@Override
	public void deleteRoleAndPermissionRelation(int roleId, String permission) {
		((IRoleDao) dao).deleteRoleAndPermissionRelation(roleId, permission);

	}

	@Override
	public void deleteRoleAndPermissionRelationByRoleId(int roleId) {
		((IRoleDao) dao).deleteRoleAndPermissionRelationByRoleId(roleId);

	}

	@Override
	public List<String> findRoleAndPermissionRelationByRoleId(int roleId) {
		return ((IRoleDao) dao).findRoleAndPermissionRelationByRoleId(roleId);
	}
}

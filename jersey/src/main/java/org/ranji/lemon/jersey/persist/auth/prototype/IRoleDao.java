package org.ranji.lemon.jersey.persist.auth.prototype;

import java.util.List;

import org.ranji.lemon.core.persist.prototype.IGenericDao;
import org.ranji.lemon.jersey.model.auth.Role;



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
 * Oauth2角色Dao接口
 * @author fengjie
 * @date 2017-10-17
 * @since JDK1.7
 * @version 1.0
 */

public interface IRoleDao extends IGenericDao<Role,Integer>{
	/**
	 * 存储  角色-操作的对应
	 * @param roleId 角色id
	 * @param permission 操作 例如：User：add
	 */
	public void saveRoleAndPermissionRelation(int roleId, String permission);

	/**
	 * 删除 角色-许可的对应
	 * @param roleId 角色id
	 * @param permission 操作
	 */
	public void deleteRoleAndPermissionRelation(int roleId, String permission);

	/**
	 * 删除 某角色的全部角色-许可的对应
	 * @param roleId 角色id
	 */
	public void deleteRoleAndPermissionRelationByRoleId(int roleId);

	/**
	 * 根据角色id查询全部的 角色-操作对应
	 * @param roelId 角色id
	 * @return 许可集合
	 */
	public List<String> findRoleAndPermissionRelationByRoleId(int roleId);
	
	
}

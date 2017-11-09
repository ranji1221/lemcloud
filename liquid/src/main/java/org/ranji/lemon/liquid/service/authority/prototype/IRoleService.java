package org.ranji.lemon.liquid.service.authority.prototype;

import java.util.List;

import org.ranji.lemon.core.service.prototype.IGenericService;
import org.ranji.lemon.liquid.model.authority.Operation;
import org.ranji.lemon.liquid.model.authority.Role;

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
 * Authority模块中的IRoleService接口类
 * @author LiJianBo
 * @date 2017-9-14
 * @since JDK1.7
 * @version 1.0
 */
public interface IRoleService extends IGenericService<Role, Integer>{

	/**
	 * 根据角色名查找角色
	 * @param roleName 角色名
	 * @return 角色
	 */
	public Role findByRoleName(String roleName);

	// 角色操作方法

	/**
	 * 存储角色-操作的对应
	 * @param roleId 角色id
	 * @param operationId 操作id
	 */
	public void saveRoleAndOperationRelation(int roleId, int operationId);

	/**
	 * 删除角色-操作的对应
	 * @param roleId 角色id
	 * @param operationId 操作id
	 */
	public void deleteRoleAndOperationRelation(int roleId, int operationId);
	
	/**
	 * 删除某角色的全部角色-操作 的对应
	 * @param roleId 用户id
	 */
	public void deleteRoleAndOperationsRelationByRoleId(int roleId);

	/**
	 * 根据角色id查询全部的角色-操作对应
	 * @param roleId 角色id
	 * @return 操作id集合
	 */
	public List<Integer> findRoleAndOperationsRelationByRoleId(int userId);
	
	/**
	 * 根据角色id查询全部的 角色-操作
	 * @author fengjie
	 * @param roelId 角色id
	 * @return 操作对象集合
	 */
	public List<Operation> findOperationByRoleId(int roleId);
	
	/**
	 * 查询全部角色，树形结构
	 * @author fengjie
	 * @return 角色
	 */
	 public List<Role> findRoleTree();
	 /**
	 * 查询角色包含角色目前数量
	 * @author fengjie
	 * @return 角色对象集合
	 */
	public List<Role> findAllCount();

}

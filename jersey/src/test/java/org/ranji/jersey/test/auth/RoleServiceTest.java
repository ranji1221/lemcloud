package org.ranji.jersey.test.auth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ranji.lemon.jersey.JerseyApplication;
import org.ranji.lemon.jersey.model.auth.Role;
import org.ranji.lemon.jersey.service.auth.prototype.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
 * Authority模块中的RoleService测试类
 * @author Fengjie
 * @date 2017-9-23
 * @since JDK1.7
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=JerseyApplication.class)  //-- 指定Spring-Boot的启动类
public class RoleServiceTest {
	@Autowired
	private IRoleService roleService;
	
	@Test
	 public void testAddRole(){
			 Role role =new Role();
			 role.setRoleName("chide");
			 roleService.save(role);
	 }
	@Test
	public void testSaveRoleAndPermissionRelation(){
		roleService.saveRoleAndPermissionRelation(2, "user:add");
	}
	
}
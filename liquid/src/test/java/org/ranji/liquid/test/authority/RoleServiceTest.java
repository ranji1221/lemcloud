package org.ranji.liquid.test.authority;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ranji.lemon.liquid.LiquidApplication;
import org.ranji.lemon.liquid.model.authority.Role;
import org.ranji.lemon.liquid.service.authority.prototype.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
@SpringBootTest(classes=LiquidApplication.class)  //-- 指定Spring-Boot的启动类
public class RoleServiceTest {
	@Autowired
	private IRoleService roleService;
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	@Test
	public void testFindRoleTree(){
		long start = System.currentTimeMillis();
		List<Role> role = roleService.findRoleTree();
		System.out.println(System.currentTimeMillis()-start);
		System.out.println(objectToJson(role));
	}
	
	 public static String objectToJson(Object data) {
	    	try {
				String string = MAPPER.writeValueAsString(data);
				return string;
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
	    	return null;
	   }
	@Test
	 public void testAddRole(){
		 
		 for(int i= 0;i<150;i++ ){
			 Role role =new Role();
			 role.setRoleName("chide");
			 role.setRoleExtendPId(9);
			 roleService.save(role);
		 }
		 
	 }
	
	@Test
	 public void testFindRole(){
		 Role r = roleService.findByRoleName("管理员");
		 System.out.println(r);
	 }
}
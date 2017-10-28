package org.ranji.liquid.test.authority;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ranji.lemon.liquid.LiquidApplication;
import org.ranji.lemon.liquid.model.authority.Role;
import org.ranji.lemon.liquid.model.authority.User;
import org.ranji.lemon.liquid.persist.authority.prototype.IUserDao;
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
 * Authority模块中的UserDao测试类
 * @author RanJi
 * @date 2017-9-5
 * @since JDK1.7
 * @version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=LiquidApplication.class)  //-- 指定Spring-Boot的启动类
public class UserDaoTest {
	
	@Autowired
	private IUserDao userDao;
	
	@Before
	public void init(){}
	
	//存储用户测试方法
	@Test
	public void testAddUser(){
			User u = new User();
			u.setUserName("zhang111sa");
			u.setUserPass("123456");
			u.setPhone("13534211234");
			u.setEmail("15423344@qq.com");
			userDao.save(u);
		
	}
	

	//存储用户与角色对应 
	@Test
	public void testSaveUserAndRoleRelation(){
		for(int i = 2; i < 10; i++){
			userDao.saveUserAndRoleRelation(1, i);
		}
	}
	
	//删除用户测试方法
	@Test
	public void testDeleteRole(){
		userDao.delete(1);
	}
	
	//删除用户与角色的对应
	@Test
	public void testDeleteUserAndRoleRelation(){
		userDao.deleteUserAndRoleRelation(1, 2);
	}
	
	//根据用户id删除用户与角色的对应
	@Test
	public void testDeleteUserAndRolesRelationByUserId(){
		userDao.deleteUserAndRolesRelationByUserId(1);
	}
	//查询所有用户
	@Test
	public void testFindAllUser(){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userName", "lisi");
		List<User> us = userDao.findAll(params);
		for(User u : us){
			System.out.println(u.getUserName());
		}
	}
	//查询某个用户测试方法
	@Test
	public void testFindUser(){
		User user = userDao.find(30000);
		System.out.println(user.getUserName());
	}
	//按用户名查找用户
	@Test
	public void testFindUserByUserName(){
		User user = userDao.findUserByUserName("222");
		System.out.println(user.getUserName());
	}
	//按用户id查找关联角色测试
	@Test
	public void testFindRolesByUserId(){
		List<Role> role = userDao.findRoleByUserId(1);
		for(Role r: role){
			System.out.println(r.getRoleName());
		}
	}
	
	//根据角色id查询用户和角色的对应
	@Test
	public void testFindUserRolesRelationByUserId(){
		List<Integer> userIds = userDao.findUserRolesRelationByUserId(1);
		for(Integer us: userIds){
			System.out.println(us);
		}
	}
	//更新用户测试方法
	@Test
	public void testUpdateUser(){
		User us = new User();
		us.setId(2);
		us.setUserName("lisi");
		us.setUserPass("23456");
		userDao.update(us);
	}
	
}
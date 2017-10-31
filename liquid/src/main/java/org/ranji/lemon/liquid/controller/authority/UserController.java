package org.ranji.lemon.liquid.controller.authority;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.ranji.lemon.core.annotation.SystemControllerLog;
import org.ranji.lemon.liquid.model.authority.User;
import org.ranji.lemon.liquid.service.authority.prototype.IAuthorityService;
import org.ranji.lemon.liquid.service.authority.prototype.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
 * Authority模块中的UserController控制器类
 * @author RanJi
 * @date 2013-10-1
 * @since JDK1.7
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/default/authority/user")
public class UserController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IAuthorityService authService;
	
	//@RequiresPermissions("user:add")
	//@SystemControllerPermission("user:add")
	@RequestMapping(value = "/add")
	//@SystemControllerLog(description="权限管理-添加用户跳转")
	public String addUser() {
		return "default/authority/user/add";
	}
	@ResponseBody
	@RequestMapping(value = "/save")
	//@SystemControllerLog(description="权限管理-添加用户")
	public String saveUser(User newUser) {
		try {
			userService.save(newUser);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "{ \"success\" : false }";
		}
	}
//	@SystemControllerPermission("user:list")
	@RequestMapping(value = "/list")
	//@SystemControllerLog(description="权限管理-用户列表")
	public String listUser() {
		return "default/authority/user/list";
	}
	
//	@SystemControllerPermission("user:adds")
	@RequestMapping(value = "/adds")
	//@SystemControllerLog(description="权限管理-批量添加用户")
	public String AddsUser() {
		return "default/authority/user/adds";
	}
	
	
//	@SystemControllerPermission("user:edit")
	@RequestMapping(value = "/edit")
	//@SystemControllerLog(description="权限管理-更新用户")
	@ResponseBody
	public String editUser(User newUser) {
		try {
			User user = userService.find(newUser.getId());
			user.setUserName(newUser.getUserName());
			user.setPhone(newUser.getPhone());
			user.setEmail(newUser.getEmail());
			userService.update(user);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "{ \"success\" : false }";
		}
	}
	
//	@SystemControllerPermission("user:auth")
	@RequestMapping(value = "/auth/{size}")
	//@SystemControllerLog(description="权限管理-给用户分配角色")
	public String authUser(@PathVariable String size) {
		if("modal".equals(size)){
			return "authority/user/authmodal";
		}else if("max".equals(size)){
			return "backend/authority/user/auth";
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/adduser")
	public String add(User obj) {
		try {
			userService.save(obj);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}


	@ResponseBody
	@RequestMapping(value = "/delete")
	public String deleteUser(int id) {
		try {
			userService.delete(id);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/deleteAll")
	//@SystemControllerLog(description="权限管理-删除多个用户")
	public String deteteAllUser(String user_ids) {
		try {
			String[] array  = user_ids.split(",");
			List <Integer> arrays = new ArrayList<Integer>();
			for(String s: array){
				arrays.add(Integer.parseInt(s));
			};
			userService.deleteByIDS(arrays);
			return "{ \"success\" : true }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"success\" : false, \"msg\" : \"操作失败\" }";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/get/{id}")
	public String get(@PathVariable int id) {
		try {
			ObjectMapper om = new ObjectMapper();
			User obj = userService.find(id);
			return om.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return "{}";
		}
	}
	
	//@SystemControllerPermission("user:list")
	@ResponseBody
	//@SystemControllerLog(description="权限管理-用户列表")
	@RequestMapping(value = "/data")
	public String data(String params,HttpSession session) {
		return authService.findAllUserInduleRoles(params);
	}
}

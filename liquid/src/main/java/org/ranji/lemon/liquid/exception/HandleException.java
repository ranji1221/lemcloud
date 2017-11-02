package org.ranji.lemon.liquid.exception;


import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.ranji.lemon.core.util.JsonUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
 * 全局异常处理
 * @author RanJi
 * @date 2017-9-12
 * @since JDK1.7
 * @version 1.0
 */

@ControllerAdvice
public class HandleException {
	/**
	 * 全局异常处理
	 * @return
	 */
	/*@ExceptionHandler(value = Exception.class)
	public ModelAndView exceptionHandler(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/error");
		return mv;
	}*/
	
	/**
	 * 没有认证
	 * @return
	 */
	@ExceptionHandler(value = UnauthenticatedException.class)
	public ModelAndView authHandler(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/login");
		return mv;
	}
	
	/**
	 * 没有授权
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = UnauthorizedException.class)
	public String authorizeHandler(){
		return JsonUtil.toJsonByProperty("access", "unauthorized");
	}
	
}

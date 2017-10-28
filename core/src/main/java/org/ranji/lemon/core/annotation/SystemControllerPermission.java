package org.ranji.lemon.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
 * 定义权限控制元注解，用于处理权限控制的注解类（针对SpringMVC中的Controller方法加标注，本来shiro其实已经提供了相关的注解类，但是由于后台总是抛出心烦的异常信息，所有自己想处理一下）
 * 设计原则和思路：
 * 元注解方式结合AOP，灵活记录操作日志
 * 替换掉shiro框架的元注解
 * 日志记录尽可能减少性能影响
 * @author RanJi
 * @date 2017-9-17
 * @since JDK1.7
 * @version 1.0
 */

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemControllerPermission {
	/**
	 *  描述某SpringMVC的控制器中的某权限信息，例如："user:list"
	 */
	String value() default "";
}

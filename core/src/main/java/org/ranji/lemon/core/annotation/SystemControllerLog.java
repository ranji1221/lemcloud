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
 * 定义日志记录元注解，用于处理后台日志的注解类（针对SpringMVC中的Controller方法加标注，实现后台日志系统）
 * 设计原则和思路：
 * 元注解方式结合AOP，灵活记录操作日志
 * 能够记录详细错误日志为运维提供支持
 * 日志记录尽可能减少性能影响
 * @author RanJi
 * @date 2017-9-14
 * @since JDK1.7
 * @version 1.0
 */

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemControllerLog {
	/**
	 *  描述某SpringMVC的控制器中的某业务操作方法，例如：XXX管理--执行XXX操作
	 */
	String description() default "";
}

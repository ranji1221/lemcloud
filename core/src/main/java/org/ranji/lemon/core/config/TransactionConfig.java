package org.ranji.lemon.core.config;

import java.util.Properties;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;
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
 * 事务配置
 * @author RanJi
 * @date 2017-10-13
 * @since JDK1.7
 * @version 1.0
 */
@Configuration
public class TransactionConfig {
	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	@Bean(name="txAdvice")
	public TransactionInterceptor getAdvisor() throws Exception{
		Properties properties = new Properties();
        properties.setProperty("*", "PROPAGATION_REQUIRED,-Exception");
        TransactionInterceptor tsi = new TransactionInterceptor(transactionManager,properties);
        return tsi;
	}
	
	
	@Bean
	public BeanNameAutoProxyCreator txProxy(){
		 BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
		 creator.setInterceptorNames("txAdvice");
		 creator.setBeanNames("*Service","*ServiceImpl");
		 creator.setProxyTargetClass(true);
		 return creator;
	}
}

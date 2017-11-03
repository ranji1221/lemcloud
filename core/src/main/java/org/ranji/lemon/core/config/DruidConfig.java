package org.ranji.lemon.core.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.druid.pool.DruidDataSource;
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
 * DRUID数据源配置
 * @author RanJi
 * @date 2017-10-13
 * @since JDK1.7
 * @version 1.0
 */
@Configuration
public class DruidConfig {
	
	 	@Value("${spring.datasource.url}")  
	    private String dbUrl;  
	    @Value("${spring.datasource.username}")  
	    private String username;  
	    @Value("${spring.datasource.password}")  
	    private String password;  
	    @Value("${spring.datasource.driverClassName}")  
	    private String driverClassName;  
	    @Value("${spring.datasource.initialSize}")  
	    private int initialSize;  
	    @Value("${spring.datasource.minIdle}")  
	    private int minIdle;  
	    @Value("${spring.datasource.maxActive}")  
	    private int maxActive;  
	    @Value("${spring.datasource.maxWait}")  
	    private int maxWait;  
	    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")  
	    private int timeBetweenEvictionRunsMillis;  
	    @Value("${spring.datasource.minEvictableIdleTimeMillis}")  
	    private int minEvictableIdleTimeMillis;  
	    @Value("${spring.datasource.validationQuery}")  
	    private String validationQuery;  
	    @Value("${spring.datasource.testWhileIdle}")  
	    private boolean testWhileIdle;  
	    @Value("${spring.datasource.testOnBorrow}")  
	    private boolean testOnBorrow;  
	    @Value("${spring.datasource.testOnReturn}")  
	    private boolean testOnReturn;  
	    @Value("${spring.datasource.poolPreparedStatements}")  
	    private boolean poolPreparedStatements;  
	    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")  
	    private int maxPoolPreparedStatementPerConnectionSize;  
	    @Value("${spring.datasource.filters}")  
	    private String filters;  
	    @Value("${spring.datasource.connectionProperties}")  
	    private String connectionProperties;  
	    @Value("${spring.datasource.useGlobalDataSourceStat}")  
	    private boolean useGlobalDataSourceStat;  
	    
	    
	    @Bean     //声明其为Bean实例  
	    public DataSource dataSource(){  
	        DruidDataSource datasource = new DruidDataSource();  
	        datasource.setUrl(dbUrl);  
	        datasource.setUsername(username);  
	        datasource.setPassword(password);  
	        datasource.setDriverClassName(driverClassName);  
	  
	        //configuration  
	        datasource.setInitialSize(initialSize);  
	        datasource.setMinIdle(minIdle);  
	        datasource.setMaxActive(maxActive);  
	        datasource.setMaxWait(maxWait);  
	        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);  
	        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);  
	        datasource.setValidationQuery(validationQuery);  
	        datasource.setTestWhileIdle(testWhileIdle);  
	        datasource.setTestOnBorrow(testOnBorrow);  
	        datasource.setTestOnReturn(testOnReturn);  
	        datasource.setPoolPreparedStatements(poolPreparedStatements);  
	        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);  
	        datasource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);  
	        try {  
	            datasource.setFilters(filters);  
	        } catch (SQLException e) {  
	            System.err.println("druid configuration initialization filter: "+ e);  
	        }  
	        datasource.setConnectionProperties(connectionProperties);  
	        return datasource;  
	    }  
}

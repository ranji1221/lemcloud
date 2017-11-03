package org.ranji.lemon.core.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
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
 * 验证码生成配置
 * @author RanJi
 * @date 2017-10-13
 * @since JDK1.7
 * @version 1.0
 */

@Configuration
public class CaptchaConfig {
	
	@Autowired
	private Environment env;
	
	@Bean(name="captchaProducer")
	public DefaultKaptcha getKaptchaBean(){
		DefaultKaptcha defaultKaptcha=new DefaultKaptcha(); 
		Properties properties=new Properties(); 
		properties.setProperty("kaptcha.border", env.getProperty("kaptcha.border").equals("true")? "yes":"no");
		properties.setProperty("kaptcha.border.color", env.getProperty("kaptcha.borderColor")); 
		properties.setProperty("kaptcha.textproducer.font.color", env.getProperty("kaptcha.textproducerFontColor")); 
		properties.setProperty("kaptcha.image.width", env.getProperty("kaptcha.imageWidth")); 
		properties.setProperty("kaptcha.image.height", env.getProperty("kaptcha.imageHeight")); 
		properties.setProperty("kaptcha.session.key", env.getProperty("kaptcha.sessionKey")); 
		properties.setProperty("kaptcha.textproducer.char.length", env.getProperty("kaptcha.textproducerCharLength"));
		properties.setProperty("kaptcha.textproducer.font.names", env.getProperty("kaptcha.textproducerFontNames"));   
		Config config=new Config(properties); 
		defaultKaptcha.setConfig(config); 
		return defaultKaptcha; 

	}
}

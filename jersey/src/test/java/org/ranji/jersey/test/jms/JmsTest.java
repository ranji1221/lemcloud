package org.ranji.jersey.test.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ranji.lemon.jersey.JerseyApplication;
import org.ranji.lemon.jersey.jms.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to you under the Apache License, Version 2.0
 * (the"License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License. Copyright [2017] [RanJi] [Email-jiran1221@163.com]
 * 
 * 测试JMS
 * 
 * @author RanJi
 * @date 2017-11-4
 * @since JDK1.7
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JerseyApplication.class) // -- 指定Spring-Boot的启动类
public class JmsTest {
	@Autowired
	private Producer producer;

	@Test
	public void contextLoads() throws InterruptedException {
		for (int i = 0; i < 1000; i++) {
			producer.sendMessage();
		}
		//Thread.sleep(5000);
	}
}

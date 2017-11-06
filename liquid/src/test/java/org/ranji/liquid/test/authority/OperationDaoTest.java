package org.ranji.liquid.test.authority;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ranji.lemon.liquid.LiquidApplication;
import org.ranji.lemon.liquid.model.authority.Operation;
import org.ranji.lemon.liquid.persist.authority.prototype.IOperationDao;
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
 * Authority模块中的ResourceDao测试类
 * @author FengJie
 * @date 2017-9-13
 * @since JDK1.7
 * @version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=LiquidApplication.class)  //-- 指定Spring-Boot的启动类
public class OperationDaoTest {
	@Autowired
	private IOperationDao operationDao;
	
	@Before
	public void init(){}
	
	//测试增加操作
	@Test
	public void testAddOperation(){
		Operation o = new Operation();
		o.setOperationName("test2");
		o.setDisplayName("测试");
		o.setOperationPId(1);
		o.setResourceId(2);
		
		operationDao.save(o);
	}
	//测试 更新操作
	@Test
	public void testUpdateOperation(){
		Operation o = new Operation();
		o.setOperationName("newTest");
		o.setDisplayName("新测试");
		o.setOperationPId(5);
		o.setResourceId(1);
		o.setId(5);
		operationDao.update(o);
	}
	//测试删除操作集
	@Test
	public void testDeleteByIDs(){
		List<Integer> l =new ArrayList<Integer>();
		l.add(1);
		operationDao.deleteByIDS(l);
		}
	//测试删除操作
	@Test
	public void testDelete(){
		operationDao.delete(4);
	}
	@Test
	public void testDeleteResourceId(){
		operationDao.deleteAllByResourceId(3);
	}
	//测试删除操作
	@Test
	public void testDeleteAll(){
		operationDao.deleteAll();
	}
	//测试查找全部操作
	@Test
	public void testFindAll(){
		List<Operation> l =operationDao.findAll();
		for(Operation o: l){
			System.out.println(o.getDisplayName());
		}
	}
	//测试查找全部操作
	@Test
	public void testFind(){
		Operation o= operationDao.find(3);
		System.out.println(o.getOperationName());
	}
}

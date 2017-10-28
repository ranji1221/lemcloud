package org.ranji.liquid.test.authority;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ranji.lemon.liquid.LiquidApplication;
import org.ranji.lemon.liquid.model.authority.Resource;
import org.ranji.lemon.liquid.persist.authority.prototype.IResourceDao;
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
public class ResourceDaoTest {
	@Autowired
	private IResourceDao resourceDao;
	
	@Before
	public void init(){}
	
	//测试增加资源
	@Test
	public void testAddResource(){
		Resource r = new Resource();
		r.setResourceName("测试");
		r.setResourceType(1);
		r.setResourceURL("www.baidu.com");
		r.setResourcePId(0);
		//r.setResourcePID(2);
		resourceDao.save(r);
	
	}
	//测试删除资源
	@Test
	public void testDeleteResource(){
		resourceDao.delete(2);
	}
	@Test
	public void testDeleteResourceByIds(){
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(3);
		resourceDao.deleteByIDS(list);
	}
	//测试查找资源
	@Test
	public void testFind(){
		Resource r=resourceDao.find(1);
		System.out.println(r.getResourceName());
	}
	//测试查找所有资源
	@Test
	public void testFindAll(){
		List<Resource> l =resourceDao.findAll();
		for(Resource r:l){
			System.out.println(r.getResourceName());
		}
	}
	//删除全部资源
	@Test
	public void TestDeleteAll(){
		resourceDao.deleteAll();
	}
	//测试删除资源集
	@Test
	public void TestDeleteByIDs(){
		List<Integer> ids =  new ArrayList<Integer>() ;
		ids.add(1);
		resourceDao.deleteByIDS(ids);
	}
	//测试更新资源
	@Test
	public void TestUpdateResource(){
		Resource r =new Resource();
		r.setId(3);
		r.setResourceName("首页");
		resourceDao.update(r);
	}
	//测试查找全部操作
	@Test
	public void TestFindROsByResourceId(){
		List<Integer> l = resourceDao.findROsByResourceId(1);
		for(Integer i: l){
			System.out.println(i);
		}
	}
}


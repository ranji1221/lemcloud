package org.ranji.lemon.core.persist.prototype;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.pagination.PagerModel;



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
 * 通用Dao接口设计
 * @author RanJi
 * @date 2014-11-12
 * @since JDK1.7
 * @version 1.0
 */
public interface IGenericDao<T, ID extends Serializable> {

	public void save(T entity);

	public void update(T entity);

	/**
	 * 根据对象ID删除数据
	 * @param oid ID(主键值)
	 */
	public void delete(ID id);

	public void deleteAll();

	/**
	 * 根据给定的用户的ID的集合，删除用户
	 * @param ids ID的集合
	 */
	public void deleteByIDS(List<ID> ids);

	/**
	 * 根据对象ID查询数据
	 * @param oid ID (主键值)
	 * @return 对象
	 */
	public T find(ID id);

	/**
	 * 查询全部的数据，可以设置条件，亦可以不设置查询条件
	 * @param params 设置条件查询的参数
	 * @return 对象集合
	 */
	public List<T> findAll(Map<String, Object> params);

	public List<T> findAll();

	/**
	 * 分页查询
	 * @param params 查询的条件参数
	 * @param methodName mybatis配置文件中所配置的方法名程
	 * @return 所要的分页数据
	 */
	public PagerModel<T> findPaginated(Map<String, Object> params);

}

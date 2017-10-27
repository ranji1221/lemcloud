package org.ranji.lemon.core.persist.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.ranji.lemon.core.pagination.PagerModel;
import org.ranji.lemon.core.persist.prototype.IGenericDao;
import org.ranji.lemon.core.system.SystemContext;
import org.springframework.beans.factory.annotation.Autowired;

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
 * 通用Dao接口实现类
 * @author RanJi
 * @date 2013-10-1
 * @since JDK1.7
 * @version 1.0
 */
public abstract class GenericDaoImpl<T, ID extends Serializable> implements IGenericDao<T, ID> {

	protected String typeNameSpace; // -- 定义MyBatis映射文件的名称空间
	private Class<T> type;

	@Autowired
	protected SqlSessionTemplate sqlSessionTemplate;

	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		// -- 获取实际子类的Class对象
		type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		typeNameSpace = type.getName();
	}

	// -- 保存实体类
	@Override
	public void save(T entity) {
		sqlSessionTemplate.insert(typeNameSpace + ".save", entity);
	}

	// -- 更新实体类
	@Override
	public void update(T entity) {
		sqlSessionTemplate.update(typeNameSpace + ".update", entity);
	}

	// -- 删除某个实体类
	@Override
	public void delete(ID id) {
		sqlSessionTemplate.delete(typeNameSpace + ".delete", id);
	}

	// -- 删除全部实体类
	@Override
	public void deleteAll() {
		sqlSessionTemplate.delete(typeNameSpace + ".deleteAll");
	}

	// -- 根据给定的ID的集合，删除用户
	@Override
	public void deleteByIDS(List<ID> ids) {
		sqlSessionTemplate.delete(typeNameSpace + ".deleteByIDs", ids);
	}

	// -- 查找某个实体
	@SuppressWarnings("unchecked")
	@Override
	public T find(ID id) {
		T entity = (T) sqlSessionTemplate.selectOne(typeNameSpace + ".find", id);
		return entity;
	}

	// -- 查找全部实体
	@Override
	public List<T> findAll() {
		return findAll(null);
	}

	// -- 查找全部实体 (设置查询条件)
	@Override
	public List<T> findAll(Map<String, Object> params) {
		List<T> entitys = sqlSessionTemplate.selectList(typeNameSpace + ".findAll", params);
		return entitys;
	}

	@Override
	public PagerModel<T> findPaginated(Map<String, Object> params) {
		// -- 1. 不管传或者不传参数都会追加至少两个分页参数
		if (params == null)
			params = new HashMap<String, Object>();
		params.put("offset", SystemContext.getOffset());
		params.put("limit", SystemContext.getPageSize());
		PagerModel<T> pm = new PagerModel<T>();
		int total = getTotalNumOfItems(params);
		List<T> entitys = sqlSessionTemplate.selectList(typeNameSpace + ".findPaginated", params);
		pm.setTotal(total);
		pm.setData(entitys);
		return pm;
	}

	// -- 获取总的条目数 (分页查询中使用)
	private int getTotalNumOfItems(Map<String, Object> params) {
		int count = (Integer) sqlSessionTemplate.selectOne(typeNameSpace + ".getTotalOfItems", params);
		return count;
	}

}

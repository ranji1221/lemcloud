package org.ranji.lemon.core.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.ranji.lemon.core.pagination.PagerModel;
import org.ranji.lemon.core.persist.prototype.IGenericDao;
import org.ranji.lemon.core.service.prototype.IGenericService;
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
 * 通用Service实现类
 * @author RanJi
 * @date 2013-10-1
 * @since JDK1.7
 * @version 1.0
 */
public abstract class GenericServiceImpl<T, ID extends Serializable> implements IGenericService<T, ID> {

	@Autowired
	protected IGenericDao<T, ID> dao;

	@Override
	public void save(T entity) {
		dao.save(entity);
	}

	@Override
	public void update(T entity) {
		dao.update(entity);
	}

	@Override
	public void delete(ID id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
	}

	@Override
	public void deleteByIDS(List<ID> ids) {
		dao.deleteByIDS(ids);
	}

	@Override
	public T find(ID id) {
		return dao.find(id);
	}

	@Override
	public List<T> findAll(Map<String, Object> params) {
		return dao.findAll(params);
	}

	@Override
	public List<T> findAll() {
		return dao.findAll();
	}

	@Override
	public PagerModel<T> findPaginated(Map<String, Object> params) {
		return dao.findPaginated(params);
	}

}

package org.ranji.lemon.liquid.persist.authority.impl;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.liquid.model.authority.Operation;
import org.ranji.lemon.liquid.persist.authority.prototype.IOperationDao;
import org.springframework.stereotype.Repository;
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
 * Authority模块中的IResourceDao实现类
 * @author FengJie
 * @date 2017-9-13
 * @since JDK1.7
 * @version 1.0
 */

@Repository
public class OperationDaoImpl extends GenericDaoImpl<Operation, Integer> implements IOperationDao {

	@Override
	public void deleteAllByResourceId(int resourceId) {
		sqlSessionTemplate.delete(typeNameSpace + ".deleteAllByResourceId", resourceId);
	}

	

}

package org.ranji.lemon.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import org.ranji.lemon.core.util.DateUtil;

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
 * 使用DDD(领域驱动设计)模式中, 定义抽象的Model类,
 * 包括公共的基础属性
 * @author RanJi
 * @date 2017-10-13
 * @since JDK1.7
 * @version 1.0
 */

public abstract class AbstractModel implements Serializable{
	private static final long serialVersionUID = -801151437171393767L;
	/**
	 * Model的自然主键
	 */
	protected int id;
	
	/**
	 * Model的创建时间
	 */
	protected Date createTime = DateUtil.now();
	/**
	 * Model的最后一次更新时间
	 */
	protected Date updateTime = DateUtil.now();
	 /**
     * Model的业务id (全球唯一标识符)
     */
    protected String guid = UUID.randomUUID().toString().replaceAll("-", "");
    
    public AbstractModel() {}
    
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid){
		this.guid = guid;
	}
	
}

package org.ranji.lemon.liquid.model.authority;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.ranji.lemon.core.model.AbstractModel;
import org.ranji.lemon.core.util.JsonUtil;

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
 * Authority模块中的Role角色类
 * @author LiJianBo
 * @date 2017-9-13
 * @since JDK1.7
 * @version 1.0
 */
public class Role extends AbstractModel{

	private static final long serialVersionUID = -2561045793807899015L;
	private int id;
	private String roleName; // 角色名称
	private String displayName; //角色名称 中文
	private int roleExtendPId; // 角色的父ID
	private String rolePName;  //父角色名（数据显示用）
	private int roleRelyId; //  角色依赖ID
	private String roleRelyName; //依赖角色名（数据显示用）
	private int roleMaxNum; // 最大限制用户数
	private String remarks;  //备注
	private List<Role> list = new ArrayList<Role>();
	private List<Operation> operationList = new ArrayList<Operation>();
	
	public List<Operation> getOperationList() {
		return operationList;
	}

	public void setOperationList(List<Operation> operationList) {
		this.operationList = operationList;
	}

	public List<Role> getList() {
		return list;
	}

	public void setList(List<Role> list) {
		this.list = list;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getRolePName() {
		return rolePName;
	}

	public void setRolePName(String rolePName) {
		this.rolePName = rolePName;
	}

	public String getRoleRelyName() {
		return roleRelyName;
	}

	public void setRoleRelyName(String roleRelyName) {
		this.roleRelyName = roleRelyName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public int getRoleExtendPId() {
		return roleExtendPId;
	}
	
	public void setRoleExtendPId(int roleExtendPId) {
		this.roleExtendPId = roleExtendPId;
	}
	
	public int getRoleRelyId() {
		return roleRelyId;
	}
	
	public void setRoleRelyId(int roleRelyId) {
		this.roleRelyId = roleRelyId;
	}
	
	public int getRoleMaxNum() {
		return roleMaxNum;
	}
	
	public void setRoleMaxNum(int roleMaxNum) {
		this.roleMaxNum = roleMaxNum;
	}
	//重写对象比较规则
	@Override
	public boolean equals(Object obj) {  
        if (this == obj)      //传入的对象就是它自己，如s.equals(s)；肯定是相等的；  
            return true;   
        if (obj == null)     //如果传入的对象是空，肯定不相等  
            return false;  
        if (getClass() != obj.getClass())  //如果不是同一个类型的，如Studnet类和Animal类，  
                                           //也不用比较了，肯定是不相等的  
            return false;  
        Role other = (Role) obj;       
        if (id == 0) {  
            if (other.id!= 0)  
                return false;  
        } else if (id!=other.getId())   //如果id属性相等，则相等  
            return false;  
        return true;  
    }  
	@Override
	public String toString() {
		return JsonUtil.objectToJson(this);
	}
}

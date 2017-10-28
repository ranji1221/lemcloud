package org.ranji.lemon.liquid.model.database;

import java.text.NumberFormat;

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
 * 数据库备份实体类
 * @author fengjie
 * @date 2017-10-18
 * @since JDK1.7
 * @version 1.0
 */

public class BackupDatabaseInfo extends AbstractModel{

	private static final long serialVersionUID = 321218137445161535L;
	
	private String infoName;    //备份名
	private String path;		//备份路径
	private String remark;		//备注
	private String fileSize;    //文件大小

	public BackupDatabaseInfo(){
		
	}
	
	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String size) {
		this.fileSize =size;
	}
	
	public void setFileSizeSave(long size){
		NumberFormat nf=NumberFormat.getNumberInstance() ; 
		nf.setMaximumFractionDigits(2);
		if(size<1024){
			fileSize =nf.format(size) +"B";
		}else if(size<1024*1024){
			this.fileSize =nf.format(size/1024.0) + "KB";
		}else if(size<1024*1024*1024){
			this.fileSize = nf.format(size/1024.0 * 1024) + "MB";
		}else if(size<1024*1024*1024*1024){
			this.fileSize = nf.format(size/1024.0 * 1024 * 1024) + "GB";
		}	
	}
	
	public String getInfoName() {
		return infoName;
	}
	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return JsonUtil.objectToJson(this);
	}

}

package org.ranji.lemon.liquid.dto;

import java.util.ArrayList;
import java.util.List;

import org.ranji.lemon.core.util.JsonUtil;

public class OperationDTO {
	
	private String operation;
	
	private String permission;
	
	private String relyName ="";
	
	private List<OperationDTO> list = new ArrayList<OperationDTO>();
	
	
	public List<OperationDTO> getList() {
		return list;
	}
	public void setList(List<OperationDTO> list) {
		this.list = list;
	}
	public String getRelyName() {
		return relyName;
	}
	public void setRelyName(String rName) {
		this.relyName = rName;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	@Override
	public String toString() {
		return JsonUtil.objectToJson(this);
	}
}

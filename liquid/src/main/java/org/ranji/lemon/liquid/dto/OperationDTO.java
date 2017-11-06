package org.ranji.lemon.liquid.dto;

import org.ranji.lemon.core.util.JsonUtil;

public class OperationDTO {
	
	private String operation;
	
	private String permission;
	
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

package com.aptnx.rbac.dto;

public class PermissionDTO {

	private int permissionKey;
	
	private String description;

	public int getPermissionKey() {
		return permissionKey;
	}

	public void setPermissionKey(int permissionKey) {
		this.permissionKey = permissionKey;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}

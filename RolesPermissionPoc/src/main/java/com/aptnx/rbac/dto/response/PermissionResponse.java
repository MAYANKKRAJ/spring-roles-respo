package com.aptnx.rbac.dto.response;

public class PermissionResponse {

	private Long permissionId;
	
    private int permissionKey;
    
    private String permissionName;

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	public int getPermissionKey() {
		return permissionKey;
	}

	public void setPermissionKey(int permissionKey) {
		this.permissionKey = permissionKey;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
    
}

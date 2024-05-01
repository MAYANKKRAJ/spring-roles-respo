package com.aptnx.rbac.dto;

import java.util.Set;

public class RolePermissionsDTO {

	private int roleKey;
	
	private Set<Integer> permissionKeys;

	public int getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(int roleKey) {
		this.roleKey = roleKey;
	}

	public Set<Integer> getPermissionKeys() {
		return permissionKeys;
	}

	public void setPermissionKeys(Set<Integer> permissionKeys) {
		this.permissionKeys = permissionKeys;
	}
	
}

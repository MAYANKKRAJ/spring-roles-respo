package com.aptnx.rbac.dto.response;

import java.util.Set;

public class RoleResponse {

	private Long roleId;
	
	private int roleKey;
	
	private String roleName;
	
	private Set<PermissionResponse> permissionResponses;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public int getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(int roleKey) {
		this.roleKey = roleKey;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<PermissionResponse> getPermissionResponses() {
		return permissionResponses;
	}

	public void setPermissionResponses(Set<PermissionResponse> permissionResponses) {
		this.permissionResponses = permissionResponses;
	}

}

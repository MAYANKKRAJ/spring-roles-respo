package com.aptnx.rbac.dto.response;

import java.util.Set;

public class UserResponse {

	private Long userId;
	
	private String userName;
	
	private RoleResponse roleResponse;
	
	private Set<PermissionResponse> permissionResponses;
	
	private Set<ResourceResponse> resourceResponses;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public RoleResponse getRoleResponse() {
		return roleResponse;
	}

	public void setRoleResponse(RoleResponse roleResponse) {
		this.roleResponse = roleResponse;
	}

	public Set<PermissionResponse> getPermissionResponses() {
		return permissionResponses;
	}

	public void setPermissionResponses(Set<PermissionResponse> permissionResponses) {
		this.permissionResponses = permissionResponses;
	}

	public Set<ResourceResponse> getResourceResponses() {
		return resourceResponses;
	}

	public void setResourceResponses(Set<ResourceResponse> resourceResponses) {
		this.resourceResponses = resourceResponses;
	}

}

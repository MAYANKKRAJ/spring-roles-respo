package com.aptnx.rbac.dto;

import java.util.Set;

import jakarta.validation.constraints.NotNull;

public class UserDTO {

	@NotNull
	private String username;
	
	@NotNull
	private Integer roleKey;
	
	private Set<Integer> permissionKeys;
	
	@NotNull
	private Set<Integer> resourceKeys;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(Integer roleKey) {
		this.roleKey = roleKey;
	}

	public Set<Integer> getPermissionKeys() {
		return permissionKeys;
	}

	public void setPermissionKeys(Set<Integer> permissionKeys) {
		this.permissionKeys = permissionKeys;
	}

	public Set<Integer> getResourceKeys() {
		return resourceKeys;
	}

	public void setResourceKeys(Set<Integer> resourceKeys) {
		this.resourceKeys = resourceKeys;
	}

}

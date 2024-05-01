package com.aptnx.rbac.entity;

import java.util.HashSet;
import java.util.Set;

import com.aptnx.rbac.enums.PermissionType;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "permissions")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PermissionEntity {
  
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private PermissionType permissionName;
    
    private int permissionKey;

    @Lob
    @Column(length = 60)
    private String description;
    
    @ManyToMany(mappedBy = "permissions")
    private Set<RoleEntity> roles = new HashSet<>();

    @ManyToMany(mappedBy = "permissions")
    private Set<UserEntity> users; // For scenarios where a permission is not assigned through roles

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PermissionType getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(PermissionType permissionName) {
		this.permissionName = permissionName;
	}
	
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
	
	public Set<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}

	public Set<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(Set<UserEntity> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "PermissionEntity [id=" + id + ", permissionName=" + permissionName + ", permissionKey=" + permissionKey
				+ ", description=" + description + ", roles=" + roles + ", users=" + users + "]";
	}
	
}

package com.aptnx.rbac.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aptnx.rbac.entity.PermissionEntity;

public interface PermissionsRepository extends JpaRepository<PermissionEntity, Long> {

	@Query("SELECT p FROM PermissionEntity p WHERE p.permissionKey IN :permissionKeys")
	Set<PermissionEntity> findByPermissionKeys(@Param("permissionKeys") Set<Integer> permissionKeys);
//	
//	@Query("SELECT p FROM PermissionEntity p WHERE p.permissionName IN :permissionNames")
//	Set<PermissionEntity> findByPermissionNames(@Param("permissionNames") List<PermissionType> permissionNames);

}


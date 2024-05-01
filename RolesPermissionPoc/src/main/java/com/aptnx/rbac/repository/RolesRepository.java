package com.aptnx.rbac.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aptnx.rbac.entity.RoleEntity;

public interface RolesRepository extends JpaRepository<RoleEntity, Long>{

	Optional<RoleEntity> findByRoleKey(int roleKey);

}

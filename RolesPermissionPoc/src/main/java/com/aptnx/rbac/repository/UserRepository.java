package com.aptnx.rbac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aptnx.rbac.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

}

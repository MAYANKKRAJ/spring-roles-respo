package com.aptnx.rbac.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aptnx.rbac.entity.ResourceAssignment;

public interface ResourceAssignmentRepository extends JpaRepository<ResourceAssignment, Long>{

}

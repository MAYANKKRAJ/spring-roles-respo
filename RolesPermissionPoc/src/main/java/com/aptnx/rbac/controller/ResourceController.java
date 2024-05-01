package com.aptnx.rbac.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aptnx.rbac.repository.ResourceAssignmentRepository;

@RestController
@RequestMapping("api/resource")
public class ResourceController {

	private final ResourceAssignmentRepository resourceRepository;
	
	public ResourceController( ResourceAssignmentRepository resourceRepository) {
		this.resourceRepository = resourceRepository;
	}
	
	
	@PostMapping("/createResource")
	public ResponseEntity<?> createResource() {
		
		
		return null;
	}
	
	
}

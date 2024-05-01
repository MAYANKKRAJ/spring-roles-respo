package com.aptnx.rbac.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aptnx.rbac.dto.UserDTO;
import com.aptnx.rbac.dto.response.UserResponse;
import com.aptnx.rbac.entity.PermissionEntity;
import com.aptnx.rbac.entity.ResourceAssignment;
import com.aptnx.rbac.entity.RoleEntity;
import com.aptnx.rbac.entity.UserEntity;
import com.aptnx.rbac.enums.ResourceType;
import com.aptnx.rbac.mapper.MapStructMapper;
import com.aptnx.rbac.repository.PermissionsRepository;
import com.aptnx.rbac.repository.ResourceAssignmentRepository;
import com.aptnx.rbac.repository.RolesRepository;
import com.aptnx.rbac.repository.UserRepository;

import jakarta.transaction.Transactional;


@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserRepository userRepository;
	private final RolesRepository rolesRepository;
	private final PermissionsRepository permissionsRepository;
	private final ResourceAssignmentRepository resourceAssignmentRepository;

	public UserController(UserRepository userRepository,
			RolesRepository rolesRepository,
			PermissionsRepository permissionsRepository, 
			ResourceAssignmentRepository resourceAssignmentRepository) {
		this.userRepository = userRepository;
		this.rolesRepository = rolesRepository;
		this.permissionsRepository = permissionsRepository;
		this.resourceAssignmentRepository = resourceAssignmentRepository;
	}

	
	@Transactional
	@PostMapping("/create")
	public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO ){
		
        List<RoleEntity> existingRoles = rolesRepository.findAll();
        List<PermissionEntity> existingPermissions = permissionsRepository.findAll();
        List<ResourceAssignment> existingResourceAssignments = resourceAssignmentRepository.findAll();
        
        RoleEntity role = getRoleByKey(existingRoles, userDTO.getRoleKey());
        if (role == null) {
            return ResponseEntity.badRequest().body("Role with key " + userDTO.getRoleKey() + " does not exist");
        }
        

		Set<PermissionEntity> permissions = new HashSet<>();
		if (userDTO.getPermissionKeys() != null) {
			Set<Integer> requestedPermissionKeys = userDTO.getPermissionKeys();
			Set<PermissionEntity> requestedPermissions = new HashSet<>(
					permissionsRepository.findByPermissionKeys(requestedPermissionKeys));
			permissions.addAll(role.getPermissions());
			permissions.addAll(requestedPermissions);
		} else {
			permissions.addAll(role.getPermissions());
		}

        
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setRoles(Collections.singleton(role));
        userEntity.setPermissions(permissions);
        
        UserEntity savedUser = userRepository.save(userEntity);
		
		  List<ResourceAssignment> resourceAssignments = new ArrayList<>();
		  if (userDTO.getResourceKeys() != null) {
		    for (int resourceKey : userDTO.getResourceKeys()) {
		      ResourceType resourceType = ResourceType.getByKey(resourceKey);
		      if (resourceType != null) {
		        ResourceAssignment existingResourceAssignment = getResourceAssignmentByKey(existingResourceAssignments, resourceKey);
		        if (existingResourceAssignment != null) {
		          resourceAssignments.add(existingResourceAssignment);
		        } else {
		          ResourceAssignment newResourceAssignment = new ResourceAssignment();
		          newResourceAssignment.setUser(userEntity);
		          newResourceAssignment.setResourceName(resourceType);
		          newResourceAssignment.setResourceKey(resourceKey);
		          resourceAssignmentRepository.save(newResourceAssignment);
		          resourceAssignments.add(newResourceAssignment);
		        }
		      } else {
		        return ResponseEntity.badRequest().body("Invalid resource key: " + resourceKey);
		      }
		    }
		  }
        
		  savedUser.setResourceAssignments(resourceAssignments);


        return ResponseEntity.ok(savedUser);
	}
	
	
    private RoleEntity getRoleByKey(List<RoleEntity> roles, int roleKey) {
        return roles.stream()
                .filter(role -> role.getRoleKey() == roleKey)
                .findFirst()
                .orElse(null);
    }
	
    private ResourceAssignment getResourceAssignmentByKey(List<ResourceAssignment> resourceAssignments, int resourceKey) {
        return resourceAssignments.stream()
                .filter(resourceAssignment -> resourceAssignment.getResourceKey() == resourceKey)
                .findFirst()
                .orElse(null);
    }
    
    
    
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
    	
    	List<UserEntity> users = userRepository.findAll();
    	
    	 List<UserResponse> userResponses = new ArrayList<>();
    	    for (UserEntity userEntity : users) {
    	        UserResponse userResponse = MapStructMapper.INSTANCE.toUserResponse(userEntity);
    	        userResponses.add(userResponse);
    	    }

    	    return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }
}

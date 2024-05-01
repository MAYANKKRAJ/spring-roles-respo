package com.aptnx.rbac.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aptnx.rbac.dto.RolePermissionsDTO;
import com.aptnx.rbac.entity.PermissionEntity;
import com.aptnx.rbac.entity.RoleEntity;
import com.aptnx.rbac.enums.PermissionType;
import com.aptnx.rbac.repository.PermissionsRepository;
import com.aptnx.rbac.repository.RolesRepository;
import com.aptnx.rbac.service.PermissionService;


@RequestMapping("/api/role")
@RestController
public class RoleController {

	private static Integer INITIAL_ROLE_KEY = 101;
	
	private final RolesRepository rolesRepository;
	private final PermissionsRepository permissionsRepository;
	private final PermissionService permissionService;
	
	public RoleController(RolesRepository rolesRepository, PermissionsRepository permissionsRepository,
			PermissionService permissionService) {
		this.rolesRepository = rolesRepository;
		this.permissionsRepository = permissionsRepository;
		this.permissionService = permissionService;
	}
	
	
	@PostMapping("/createRole")
    public ResponseEntity<?> addNewRole(@RequestParam String roleName) {
        List<RoleEntity> existingRoles = rolesRepository.findAll();

        boolean roleNameExists = existingRoles.stream()
                .anyMatch(role -> role.getRoleName().equalsIgnoreCase(roleName));

        if (roleNameExists) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Role name already exists");
            return ResponseEntity.badRequest().body(response);
        }

        int maxRoleKey = existingRoles.stream()
                .mapToInt(RoleEntity::getRoleKey)
                .max()
                .orElse(INITIAL_ROLE_KEY - 1); 

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName(roleName.toUpperCase());
        roleEntity.setRoleKey(maxRoleKey + 1); 
        RoleEntity savedRole = rolesRepository.save(roleEntity);

        return new ResponseEntity<>(savedRole, HttpStatus.OK);
    }
	
	
	@PostMapping("/addPermissions")
	public ResponseEntity<?> addPermissionsToRoleKey(@RequestBody RolePermissionsDTO rolePermissionsDTO){
		
		Optional<RoleEntity> optionalRole = rolesRepository.findByRoleKey(rolePermissionsDTO.getRoleKey());
		if (optionalRole.isEmpty()) {
			Map<String, String> response = new HashMap<>();
			response.put("message", "Role with key " + rolePermissionsDTO.getRoleKey() + " does not exist");
			return ResponseEntity.badRequest().body(response);
		}
		
        RoleEntity role = optionalRole.get();
        
        List<String> existingPermissions = permissionsRepository.findAll().stream()
                .map(permission -> permission.getPermissionName().getPermissionValue())
                .collect(Collectors.toList());
		 
        List<Integer> invalidPermissionKeys = new ArrayList<>();
        for (int permissionKey : rolePermissionsDTO.getPermissionKeys()) {
            PermissionType permissionType = PermissionType.getByKey(permissionKey);
            if (permissionType == null || !existingPermissions.contains(permissionType.getPermissionValue())) {
                invalidPermissionKeys.add(permissionKey);
            }
        }
        
        if (!invalidPermissionKeys.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Invalid permission keys: " + invalidPermissionKeys);
            return ResponseEntity.badRequest().body(response);
        }
		
        Set<PermissionEntity> permissions = permissionsRepository.findByPermissionKeys(rolePermissionsDTO.getPermissionKeys());
        
        role.getPermissions().addAll(permissions);
        rolesRepository.save(role);
		
        return ResponseEntity.ok(role);
	}

	
	@GetMapping("/roles")
	public ResponseEntity<?> getAllRoles(){
		List<RoleEntity> roles = rolesRepository.findAll();
		return ResponseEntity.ok(roles);
	}
	
	@GetMapping("/roleKey")
	public ResponseEntity<?> getRoleById(@RequestParam int roleKey){
		RoleEntity role = rolesRepository.findByRoleKey(roleKey).get();
		return ResponseEntity.ok(role);
	}
}

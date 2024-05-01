package com.aptnx.rbac.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aptnx.rbac.dto.PermissionDTO;
import com.aptnx.rbac.entity.PermissionEntity;
import com.aptnx.rbac.enums.PermissionType;
import com.aptnx.rbac.repository.PermissionsRepository;

@Service
public class PermissionService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PermissionService.class);
	
	private final PermissionsRepository permissionsRepository;
	
	public PermissionService(PermissionsRepository permissionsRepository) {
		this.permissionsRepository = permissionsRepository;
	}
	
	public List<PermissionDTO> saveNewPermissions(List<PermissionDTO> permissions) {
	
		LOGGER.info("saving new permissions");
		
	    List<PermissionEntity> newPermissions = new ArrayList<>();
	    List<String> existingPermissionNames = getExistingPermissionNames();
	    LOGGER.info("fetching existing permissions");
	    
	    List<PermissionDTO> newlyAddedPermissionsDTO = new ArrayList<>();

	    for (PermissionDTO permissionDTO : permissions) {
	        PermissionType permissionType = PermissionType.getByKey(permissionDTO.getPermissionKey());
	        if (permissionType == null) {
	        	LOGGER.info("invalid permission key: {}", permissionDTO.getPermissionKey());
	            throw new IllegalArgumentException("Invalid permission key: " + permissionDTO.getPermissionKey());
	        }

	        boolean permissionExists = existingPermissionNames.contains(permissionType.getPermissionValue());
	        if (permissionExists) {
	        	LOGGER.info("permission key: {}, already exists ", permissionDTO.getPermissionKey());
	            continue; 
	        }

	        PermissionEntity permissionEntity = new PermissionEntity();
	        permissionEntity.setPermissionKey(permissionType.getPermissionKey());
	        permissionEntity.setPermissionName(permissionType);
	        permissionEntity.setDescription(permissionDTO.getDescription());

	        newPermissions.add(permissionEntity);

	        PermissionDTO newPermissionDTO = new PermissionDTO();
	        newPermissionDTO.setPermissionKey(permissionType.getPermissionKey());
	        newPermissionDTO.setDescription(permissionDTO.getDescription());
	        newlyAddedPermissionsDTO.add(newPermissionDTO);
	    }

	    if (!newPermissions.isEmpty()) {
	        permissionsRepository.saveAll(newPermissions);
	        LOGGER.info("permissions saved in db: {}", newPermissions);
	    } else {
	    	LOGGER.info("no new permissions found to be added in db");
			return Collections.singletonList(new PermissionDTO());
		}

	    return newlyAddedPermissionsDTO;
	}
   
    
    public List<String> getExistingPermissionNames() {
        List<PermissionEntity> existingPermissions = permissionsRepository.findAll();
        return existingPermissions.stream()
                .map(permission -> permission.getPermissionName().getPermissionValue())
                .collect(Collectors.toList());
    }

}

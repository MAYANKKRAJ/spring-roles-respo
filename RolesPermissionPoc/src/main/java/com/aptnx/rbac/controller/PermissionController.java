package com.aptnx.rbac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aptnx.rbac.dto.PermissionDTO;
import com.aptnx.rbac.service.PermissionService;


@RestController
@RequestMapping("/api/permission")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	
    @PostMapping("/save")
    public ResponseEntity<?> savePermissions(@RequestBody List<PermissionDTO> permissions) {
        try {
            List<?> newlyAddedPermissions = permissionService.saveNewPermissions(permissions);
            return ResponseEntity.ok().body(newlyAddedPermissions);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
	
}

package com.aptnx.rbac.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.aptnx.rbac.dto.response.PermissionResponse;
import com.aptnx.rbac.dto.response.ResourceResponse;
import com.aptnx.rbac.dto.response.RoleResponse;
import com.aptnx.rbac.dto.response.UserResponse;
import com.aptnx.rbac.entity.PermissionEntity;
import com.aptnx.rbac.entity.ResourceAssignment;
import com.aptnx.rbac.entity.RoleEntity;
import com.aptnx.rbac.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

	MapStructMapper INSTANCE = Mappers.getMapper(MapStructMapper.class);
	
    @Mapping(target = "userId", source = "userEntity.id")
    @Mapping(target = "userName", source = "userEntity.username")
    @Mapping(target = "roleResponse", source = "userEntity.roles")
    @Mapping(target = "permissionResponses", source = "userEntity.permissions")
    @Mapping(target = "resourceResponses", source = "userEntity.resourceAssignments")
    UserResponse toUserResponse(UserEntity userEntity);
    
    @Mapping(target = "roleId", source = "roleEntity.id")
    @Mapping(target = "roleKey", source = "roleEntity.roleKey")
    @Mapping(target = "roleName", source = "roleEntity.roleName")
    @Mapping(target = "permissionResponses", source = "roleEntity.permissions")
    RoleResponse toRoleResponse(RoleEntity roleEntity);
    
    @Mapping(target = "permissionId", source = "permissionEntity.id")
    @Mapping(target = "permissionKey", source = "permissionEntity.permissionKey")
    @Mapping(target = "permissionName", source = "permissionEntity.permissionName")
    PermissionResponse toPermissionResponse(PermissionEntity permissionEntity);
    
    @Mapping(target = "resourceKey", source = "resourceAssignment.resourceKey")
    @Mapping(target = "resourceName", expression = "java(resourceAssignment.getResourceName().getResourceValue())")
    ResourceResponse toResourceResponse(ResourceAssignment resourceAssignment);
    
    default RoleResponse map(Set<RoleEntity> roles) {
        if (roles == null || roles.isEmpty()) {
            return null;
        }
        RoleEntity roleEntity = roles.iterator().next();
        return toRoleResponse(roleEntity);
    }
    
    
    default List<PermissionResponse> mapPermissions(Set<PermissionEntity> permissions) {
        List<PermissionResponse> permissionResponses = new ArrayList<>();
        if (permissions != null && !permissions.isEmpty()) {
            for (PermissionEntity permissionEntity : permissions) {
                if (permissionEntity != null) {
                    PermissionResponse permissionResponse = new PermissionResponse();
                    permissionResponse.setPermissionId(permissionEntity.getId());
                    permissionResponse.setPermissionKey(permissionEntity.getPermissionKey());
                    permissionResponse.setPermissionName(permissionEntity.getPermissionName().getPermissionValue()); 
                    permissionResponses.add(permissionResponse);
                }
            }
        }
        return permissionResponses;
    }
    
    
    default List<ResourceResponse> mapResourceAssignments(List<ResourceAssignment> resourceAssignments) {
    	List<ResourceResponse> resourceResponses = new ArrayList<>();
        if (resourceAssignments != null && !resourceAssignments.isEmpty()) {
           for(ResourceAssignment resource :resourceAssignments) {
        	   if(resource != null) {
        		   ResourceResponse resourceResponse = new ResourceResponse();
                   resourceResponse.setResourceKey(resource.getResourceKey());
                   resourceResponse.setResourceName(resource.getResourceName().getResourceValue());
                   resourceResponses.add(resourceResponse);
        	   }
           }
        }
        return resourceResponses;
    }
	
}


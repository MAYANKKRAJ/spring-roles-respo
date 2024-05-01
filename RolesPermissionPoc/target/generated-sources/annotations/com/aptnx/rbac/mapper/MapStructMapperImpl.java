package com.aptnx.rbac.mapper;

import com.aptnx.rbac.dto.response.PermissionResponse;
import com.aptnx.rbac.dto.response.ResourceResponse;
import com.aptnx.rbac.dto.response.RoleResponse;
import com.aptnx.rbac.dto.response.UserResponse;
import com.aptnx.rbac.entity.PermissionEntity;
import com.aptnx.rbac.entity.ResourceAssignment;
import com.aptnx.rbac.entity.RoleEntity;
import com.aptnx.rbac.entity.UserEntity;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-30T16:34:36+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230814-2020, environment: Java 17.0.9 (Eclipse Adoptium)"
)
@Component
public class MapStructMapperImpl implements MapStructMapper {

    @Override
    public UserResponse toUserResponse(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setUserId( userEntity.getId() );
        userResponse.setUserName( userEntity.getUsername() );
        userResponse.setRoleResponse( map( userEntity.getRoles() ) );
        userResponse.setPermissionResponses( permissionEntitySetToPermissionResponseSet( userEntity.getPermissions() ) );
        userResponse.setResourceResponses( resourceAssignmentListToResourceResponseSet( userEntity.getResourceAssignments() ) );

        return userResponse;
    }

    @Override
    public RoleResponse toRoleResponse(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        RoleResponse roleResponse = new RoleResponse();

        roleResponse.setRoleId( roleEntity.getId() );
        roleResponse.setRoleKey( roleEntity.getRoleKey() );
        roleResponse.setRoleName( roleEntity.getRoleName() );
        roleResponse.setPermissionResponses( permissionEntitySetToPermissionResponseSet( roleEntity.getPermissions() ) );

        return roleResponse;
    }

    @Override
    public PermissionResponse toPermissionResponse(PermissionEntity permissionEntity) {
        if ( permissionEntity == null ) {
            return null;
        }

        PermissionResponse permissionResponse = new PermissionResponse();

        permissionResponse.setPermissionId( permissionEntity.getId() );
        permissionResponse.setPermissionKey( permissionEntity.getPermissionKey() );
        if ( permissionEntity.getPermissionName() != null ) {
            permissionResponse.setPermissionName( permissionEntity.getPermissionName().name() );
        }

        return permissionResponse;
    }

    @Override
    public ResourceResponse toResourceResponse(ResourceAssignment resourceAssignment) {
        if ( resourceAssignment == null ) {
            return null;
        }

        ResourceResponse resourceResponse = new ResourceResponse();

        resourceResponse.setResourceKey( resourceAssignment.getResourceKey() );

        resourceResponse.setResourceName( resourceAssignment.getResourceName().getResourceValue() );

        return resourceResponse;
    }

    protected Set<PermissionResponse> permissionEntitySetToPermissionResponseSet(Set<PermissionEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<PermissionResponse> set1 = new LinkedHashSet<PermissionResponse>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PermissionEntity permissionEntity : set ) {
            set1.add( toPermissionResponse( permissionEntity ) );
        }

        return set1;
    }

    protected Set<ResourceResponse> resourceAssignmentListToResourceResponseSet(List<ResourceAssignment> list) {
        if ( list == null ) {
            return null;
        }

        Set<ResourceResponse> set = new LinkedHashSet<ResourceResponse>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( ResourceAssignment resourceAssignment : list ) {
            set.add( toResourceResponse( resourceAssignment ) );
        }

        return set;
    }
}

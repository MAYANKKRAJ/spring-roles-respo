package com.aptnx.rbac.enums;

public enum PermissionType {

	  // Super ADMIN specific permissions
	  PERMISSION_MANAGE_USERS(101, "Manage Users"),
	  PERMISSION_MANAGE_ROLES(102, "Manage Roles"),
	  PERMISSION_MANAGE_PERMISSIONS(103, "Manage Permissions"),

	  // User management permissions
	  PERMISSION_ADD_USER(104, "Add User"),
	  PERMISSION_DELETE_USER(105, "Delete User"),
	  PERMISSION_UPDATE_USER(106, "Update User"),

	  // Grant specific permissions
	  PERMISSION_ACCESS_GRANT_SERVICE(107, "Access Grant Service"),
	  PERMISSION_CREATE_GRANT(108, "Create Grant"),
	  PERMISSION_UPDATE_GRANT(109, "Update Grant"),
	  PERMISSION_APPROVE_GRANT(110, "Approve Grant"),
	  PERMISSION_REJECT_GRANT(111, "Reject Grant"),

	  // Investment specific permissions
	  PERMISSION_ACCESS_INVESTMENT_SERVICE(112, "Access Investment Service"),
	  PERMISSION_CREATE_INVESTMENT(113, "Create Investment"),
	  PERMISSION_UPDATE_INVESTMENT(114, "Update Investment"),
	  PERMISSION_APPROVE_INVESTMENT(115, "Approve Investment"),
	  PERMISSION_REJECT_INVESTMENT(116, "Reject Investment");

	  private final int permissionKey;
	  private final String permissionValue;

	  PermissionType(int permissionKey, String permissionValue) {
	    this.permissionKey = permissionKey;
	    this.permissionValue = permissionValue;
	  }

	  public int getPermissionKey() {
	    return permissionKey;
	  }

	  public String getPermissionValue() {
	    return permissionValue;
	  }
	  
		public static PermissionType getByKey(int permissionKey) {
			for (PermissionType type : PermissionType.values()) {
				if (type.permissionKey == permissionKey) {
					return type;
				}
			}
			return null;
		}
		
		public static PermissionType getByValue(String  permissionValue) {
			for (PermissionType type : PermissionType.values()) {
				if (type.permissionValue.equals(permissionValue)) {
					return type;
				}
			}
			return null;
		}
	}



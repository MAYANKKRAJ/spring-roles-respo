package com.aptnx.rbac.enums;

public enum ResourceType {

	  RESOURCE_GRANT(1, "Grant"),
	  RESOURCE_INVESTMENT(2, "Investment"),
	  RESOURCE_JOURNAL(3, "Journal"),
	  RESOURCE_COMMITTEE(4, "Committee"),
	  RESOURCE_COMMUNICATION(5, "Communication"),
	  RESOURCE_DEBT(6, "Debt");

	  private final int resourceKey;
	  private final String resourceValue;

	  ResourceType(int resourceKey, String resourceValue) {
	    this.resourceKey = resourceKey;
	    this.resourceValue = resourceValue;
	  }

	  public int getResourceKey() {
	    return resourceKey;
	  }

	  public String getResourceValue() {
	    return resourceValue;
	  }

	  public static ResourceType getByKey(int resourceKey) {
	    for (ResourceType type : ResourceType.values()) {
	      if (type.resourceKey == resourceKey) {
	        return type;
	      }
	    }
	    return null;
	  }
	}

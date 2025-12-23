package com.plazoleta.infrastructure.out.jpa.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public enum Role {

	
	ADMINISTRATOR(Arrays.asList(
			RolePermission.READ_ALL_USERS,
	        RolePermission.READ_ONE_USERS,
	        RolePermission.CREATE_ONE_USERS,
	        RolePermission.DISABLE_ONE_USERS,

	        RolePermission.READ_ALL_RESTAURANT,
	        RolePermission.READ_ONE_RESTAURANT,
	        RolePermission.CREATE_ONE_RESTAURANT,
	        RolePermission.DISABLE_ONE_RESTAURANT

	    )),
		OWNER(Arrays.asList(
				RolePermission.READ_ALL_PLATE,
		        RolePermission.READ_ONE_PLATE,
		        RolePermission.CREATE_ONE_PLATE,
		        RolePermission.DISABLE_ONE_PLATE,
		        RolePermission.CREATE_ONE_EMPLOYEE,
		        RolePermission.READ_ONE_EMPLOYEE
		    )),
		EMPLOYEE((Arrays.asList(	
				
		    )));
	
	  private List<RolePermission> permissions;

    Role(List<RolePermission> permissions) {
        this.permissions = permissions;
    }

    public List<RolePermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<RolePermission> permissions) {
        this.permissions = permissions;
    }
}

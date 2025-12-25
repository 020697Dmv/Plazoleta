package com.plazoleta.infrastructure.out.jpa.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public enum Role {

	
	ADMINISTRATOR(Arrays.asList(
			RolePermission.ADMINISTRATOR

	    )),
		OWNER(Arrays.asList(
				RolePermission.OWNER
		    )),
		EMPLOYEE((Arrays.asList(	
				RolePermission.EMPLOYEE

		    ))),
		CLIENT((Arrays.asList(	
				 RolePermission.CLIENT
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

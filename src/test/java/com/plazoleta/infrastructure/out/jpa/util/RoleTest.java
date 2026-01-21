package com.plazoleta.infrastructure.out.jpa.util;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    void permissions_for_each_role_are_set() {
        assertTrue(Role.ADMINISTRATOR.getPermissions().contains(RolePermission.ADMINISTRATOR));
        assertTrue(Role.OWNER.getPermissions().contains(RolePermission.OWNER));
        assertTrue(Role.EMPLOYEE.getPermissions().contains(RolePermission.EMPLOYEE));
        assertTrue(Role.CLIENT.getPermissions().contains(RolePermission.CLIENT));
    }

    @Test
    void setPermissions_allows_updating_permissions_list() {
        List<RolePermission> original = Role.CLIENT.getPermissions();
        Role.CLIENT.setPermissions(List.of(RolePermission.OWNER));
        assertEquals(1, Role.CLIENT.getPermissions().size());
        assertTrue(Role.CLIENT.getPermissions().contains(RolePermission.OWNER));

        // restore original to avoid side effects for other tests
        Role.CLIENT.setPermissions(original);
    }
}

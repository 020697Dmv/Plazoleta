package com.plazoleta.infrastructure.out.jpa.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RolePermissionTest {

    @Test
    void rolePermission_values_contains_expected() {
        List<RolePermission> perms = Arrays.asList(RolePermission.values());
        assertTrue(perms.contains(RolePermission.ADMINISTRATOR));
        assertTrue(perms.contains(RolePermission.OWNER));
        assertTrue(perms.contains(RolePermission.EMPLOYEE));
        assertTrue(perms.contains(RolePermission.CLIENT));
        assertEquals(4, perms.size());
    }
}

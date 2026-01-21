package com.plazoleta.domain.model;

import com.plazoleta.infrastructure.out.jpa.util.Role;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserModelTest {

    @Test
    void gettersAndSetters_work() {
        User u = new User();
        u.setId(10L);
        u.setName("Juan");
        u.setLastname("Perez");
        u.setPhone("+573001234567");
        u.setBirthdate(LocalDate.of(1990,1,1));
        u.setEmail("j@p.com");
        u.setPassword("pwd");
        u.setRole(Role.CLIENT);

        assertEquals(10L, u.getId());
        assertEquals("Juan", u.getName());
        assertEquals("Perez", u.getLastname());
        assertEquals("+573001234567", u.getPhone());
        assertEquals(LocalDate.of(1990,1,1), u.getBirthdate());
        assertEquals("j@p.com", u.getEmail());
        assertEquals("pwd", u.getPassword());
        assertEquals(Role.CLIENT, u.getRole());
    }

}

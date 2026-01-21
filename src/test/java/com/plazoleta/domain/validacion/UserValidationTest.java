package com.plazoleta.domain.validacion;

import com.plazoleta.domain.model.User;
import com.plazoleta.domain.exception.DomainExcepcion;
import com.plazoleta.infrastructure.out.jpa.util.Role;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserValidationTest {

    @Test
    void validateUser_null_throws() {
        assertThrows(DomainExcepcion.class, () -> UserValidation.validateUser(null));
    }

    @Test
    void validateUser_client_withoutBirth_ok() {
        User u = new User(1L, "Nombre", "Apellido", "+573001234567", null, "a@b.com", "pass", Role.CLIENT);
        assertDoesNotThrow(() -> UserValidation.validateUser(u));
    }

    @Test
    void validateUser_admin_underage_throws() {
        User u = new User(2L, "Admin", "One", "+573001234567", LocalDate.now().minusYears(17), "admin@x.com", "p", Role.ADMINISTRATOR);
        assertThrows(DomainExcepcion.class, () -> UserValidation.validateUser(u));
    }

    @Test
    void validateUser_admin_ok() {
        User u = new User(3L, "Admin", "Two", "+573001234567", LocalDate.now().minusYears(25), "admin2@x.com", "p", Role.ADMINISTRATOR);
        assertDoesNotThrow(() -> UserValidation.validateUser(u));
    }

}

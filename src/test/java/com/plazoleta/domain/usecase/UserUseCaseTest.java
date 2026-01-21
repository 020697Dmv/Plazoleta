package com.plazoleta.domain.usecase;

import com.plazoleta.domain.api.IUserServicePort;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.model.User;
import com.plazoleta.domain.spi.IRestaurantEmployeePersistencePort;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.domain.spi.IUserPersistencePort;
import com.plazoleta.infrastructure.out.jpa.util.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IRestaurantEmployeePersistencePort restaurantEmployeePersistencePort;

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserUseCase userUseCase;

    @BeforeEach
    void before() {
        SecurityContextHolder.clearContext();
        // create instance manually so we can inject the non-final passwordEncoder
        userUseCase = new UserUseCase(userPersistencePort, restaurantEmployeePersistencePort, restaurantPersistencePort);
        org.springframework.test.util.ReflectionTestUtils.setField(userUseCase, "passwordEncoder", passwordEncoder);
    }

    @AfterEach
    void after() {
        SecurityContextHolder.clearContext();
    }

    private void setAuthenticationWithRoles(String... roles) {
        Authentication auth = mock(Authentication.class);
        List<GrantedAuthority> authorities = java.util.Arrays.stream(roles)
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
        doReturn(authorities).when(auth).getAuthorities();
        SecurityContext ctx = SecurityContextHolder.createEmptyContext();
        ctx.setAuthentication(auth);
        SecurityContextHolder.setContext(ctx);
    }

    @Test
    void saveUser_ownerWithoutAdmin_returnsPermissionMessage() {
        User u = new User(1L, "Nombre", "Apellido", "+573001234567", null, "a@b.com", "pwd", Role.OWNER);
        setAuthenticationWithRoles("CLIENT");

        MessageResponse resp = userUseCase.saveUser(u, 1L);
        assertEquals("You do not have permission to perform this action", resp.getMessage());
        verifyNoInteractions(userPersistencePort);
    }

    @Test
    void saveUser_existsById_returnsAlreadyExists() {
        User u = new User(2L, "Nombre", "Apellido", "+573001234567", null, "a@b.com", "pwd", Role.CLIENT);
        setAuthenticationWithRoles(Role.ADMINISTRATOR.name());
        when(userPersistencePort.existsById(2L)).thenReturn(true);

        MessageResponse resp = userUseCase.saveUser(u, null);
        assertEquals("There is already a user with that ID", resp.getMessage());
        verify(userPersistencePort).existsById(2L);
        verify(userPersistencePort, never()).saveUser(any());
    }

    @Test
    void saveUser_client_success_createsUser() {
        User u = new User(3L, "Nombre", "Apellido", "+573001234567", null, "a@b.com", "pwd", Role.CLIENT);
        setAuthenticationWithRoles(Role.ADMINISTRATOR.name());
        when(userPersistencePort.existsById(3L)).thenReturn(false);
        when(passwordEncoder.encode("pwd")).thenReturn("encoded");
        User saved = new User(3L, "Nombre", "Apellido", "+573001234567", null, "a@b.com", "encoded", Role.CLIENT);
        when(userPersistencePort.saveUser(any())).thenReturn(saved);

        MessageResponse resp = userUseCase.saveUser(u, null);
        assertEquals("User created with id 3", resp.getMessage());
        verify(passwordEncoder).encode("pwd");
        verify(userPersistencePort).saveUser(any());
    }

    @Test
    void saveUser_employee_triggersRestaurantEmployeeSave() {
        User u = new User(4L, "Emp", "Apellido", "+573001234567", null, "e@b.com", "pwd", Role.EMPLOYEE);
        setAuthenticationWithRoles(Role.OWNER.name());
        when(userPersistencePort.existsById(4L)).thenReturn(false);
        when(passwordEncoder.encode("pwd")).thenReturn("enc");

        Restaurant r = new Restaurant();
        r.setNit(10L);
        r.setIdentity_document_owner(99L);
        when(restaurantPersistencePort.findById(10L)).thenReturn(r);

        User owner = new User(99L, "Owner", "O", "+573009999999", null, "o@x.com", "p", Role.OWNER);
        when(userPersistencePort.findById(99L)).thenReturn(owner);

        User saved = new User(4L, "Emp", "Apellido", "+573001234567", null, "e@b.com", "enc", Role.EMPLOYEE);
        when(userPersistencePort.saveUser(any())).thenReturn(saved);

        MessageResponse resp = userUseCase.saveUser(u, 10L);

        assertEquals("User created with id 4", resp.getMessage());
        verify(restaurantPersistencePort).findById(10L);
        verify(userPersistencePort).findById(99L);
        verify(restaurantEmployeePersistencePort).saveRestaurantEmployee(eq(r), eq(owner), any());
    }

}

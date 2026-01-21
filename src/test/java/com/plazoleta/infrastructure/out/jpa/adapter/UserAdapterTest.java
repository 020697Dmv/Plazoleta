package com.plazoleta.infrastructure.out.jpa.adapter;

import com.plazoleta.domain.model.User;
import com.plazoleta.infrastructure.exception.UserNotFoundException;
import com.plazoleta.infrastructure.out.jpa.entity.UserEntity;
import com.plazoleta.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.plazoleta.infrastructure.out.jpa.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserAdapterTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IUserEntityMapper userEntidadMapeo;

    @InjectMocks
    private UserAdapter userAdapter;

    @Test
    void saveUser_mapsAndSaves() {
        User input = new User();
        UserEntity entity = new UserEntity();
        UserEntity saved = new UserEntity();
        User out = new User();

        when(userEntidadMapeo.toEntity(input)).thenReturn(entity);
        when(userRepository.save(entity)).thenReturn(saved);
        when(userEntidadMapeo.toUser(saved)).thenReturn(out);

        User result = userAdapter.saveUser(input);

        assertSame(out, result);
        verify(userEntidadMapeo).toEntity(input);
        verify(userRepository).save(entity);
        verify(userEntidadMapeo).toUser(saved);
    }

    @Test
    void findById_existing_returnsUser() {
        UserEntity entity = new UserEntity();
        User out = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(userEntidadMapeo.toUser(entity)).thenReturn(out);

        User result = userAdapter.findById(1L);

        assertSame(out, result);
        verify(userRepository).findById(1L);
        verify(userEntidadMapeo).toUser(entity);
    }

    @Test
    void findById_missing_throws() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userAdapter.findById(2L));
    }

    @Test
    void existsById_delegatesToRepository() {
        when(userRepository.existsById(5L)).thenReturn(true);
        assertTrue(userAdapter.existsById(5L));
        verify(userRepository).existsById(5L);
    }
}

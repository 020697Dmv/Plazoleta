package com.plazoleta.domain.handler;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.plazoleta.application.dto.request.UserRequesteDto;
import com.plazoleta.application.dto.response.StringResponseDto;
import com.plazoleta.application.handler.impl.UserHandler;
import com.plazoleta.application.mapper.IMensaggeResponseMapper;
import com.plazoleta.application.mapper.IUserRequestMapper;
import com.plazoleta.application.mapper.IUserResponseMapper;
import com.plazoleta.domain.api.IUserServicePort;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserHandlerTest {
	
	  	@Mock
	    private IUserServicePort usuarioServicioPort;

	    @Mock
	    private IUserRequestMapper usuarioRequestMapper;

	    @Mock
	    private IUserResponseMapper usuarioResponseMapper;

	    @Mock
	    private IMensaggeResponseMapper StringMessageResponse;

	    @InjectMocks
	    private UserHandler userHandler;

	    private UserRequesteDto userRequestDto;
	    private User user;
	    private MessageResponse messageResponse;
	    private StringResponseDto stringResponseDto;

	    @BeforeEach
	    void setUp() {
	        userRequestDto = new UserRequesteDto();
	        user = new User();
	        user.setId(1L);
	        user.setName("John");
	        user.setLastname("Doe");
	        user.setEmail("john@example.com");
	        
	        messageResponse = new MessageResponse("User created successfully");
	        stringResponseDto = new StringResponseDto();
	        stringResponseDto.setMensaje("User created successfully");
	    }

	    @Test
	    void testGuardarUsuario_Success() {
	        when(usuarioRequestMapper.aUsuario(userRequestDto)).thenReturn(user);
	        when(usuarioServicioPort.saveUser(user)).thenReturn(messageResponse);
	      //  when(StringMessageResponse.toResponse(messageResponse)).thenReturn(stringResponseDto);

	        MessageResponse result = userHandler.saveUser(userRequestDto);

	        assertNotNull(result);
	        assertEquals("User created successfully", result.getMessage());
	        verify(usuarioRequestMapper).aUsuario(userRequestDto);
	        verify(usuarioServicioPort).saveUser(user);
	      //  verify(StringMessageResponse).toResponse(messageResponse);
	    }

	    @Test
	    void testGuardarUsuario_MapperInteractions() {
	        // Given
	        when(usuarioRequestMapper.aUsuario(userRequestDto)).thenReturn(user);
	        when(usuarioServicioPort.saveUser(user)).thenReturn(messageResponse);
	      //  when(StringMessageResponse.toResponse(messageResponse)).thenReturn(stringResponseDto);

	        // When
	        userHandler.saveUser(userRequestDto);

	        // Then
	        verify(usuarioRequestMapper, times(1)).aUsuario(userRequestDto);
	       // verify(StringMessageResponse, times(1)).toResponse(messageResponse);
	        verifyNoInteractions(usuarioResponseMapper); // This mapper is not used in the method
	    }

	    @Test
	    void testGuardarUsuario_ServicePortCalled() {
	        // Given
	        when(usuarioRequestMapper.aUsuario(userRequestDto)).thenReturn(user);
	        when(usuarioServicioPort.saveUser(user)).thenReturn(messageResponse);
	      //  when(StringMessageResponse.toResponse(messageResponse)).thenReturn(stringResponseDto);

	        // When
	        userHandler.saveUser(userRequestDto);

	        // Then
	        verify(usuarioServicioPort).saveUser(argThat(u -> 
	            u.getId().equals(1L) && 
	            u.getName().equals("John") && 
	            u.getLastname().equals("Doe")
	        ));
	    }

	    @Test
	    void testGuardarUsuario_ResponseMappingCorrect() {
	        // Given
	        MessageResponse specificMessage = new MessageResponse("Usuario created with id 123");
	        StringResponseDto specificResponse = new StringResponseDto();
	        specificResponse.setMensaje("Usuario created with id 123");
	        
	        when(usuarioRequestMapper.aUsuario(userRequestDto)).thenReturn(user);
	        when(usuarioServicioPort.saveUser(user)).thenReturn(specificMessage);
	       // when(StringMessageResponse.toResponse(specificMessage)).thenReturn(specificResponse);

	        // When
	        MessageResponse result = userHandler.saveUser(userRequestDto);

	        // Then
	        assertNotNull(result);
	        assertEquals("Usuario created with id 123", result.getMessage());
	      //  verify(StringMessageResponse).toResponse(specificMessage);
	    }

	    @Test
	    void testGuardarUsuario_NullUserRequestDto() {
	        // Given
	        when(usuarioRequestMapper.aUsuario(null)).thenReturn(null);
	        when(usuarioServicioPort.saveUser(null)).thenThrow(new RuntimeException("User cannot be null"));

	        // When & Then
	        assertThrows(RuntimeException.class, () -> userHandler.saveUser(null));
	        verify(usuarioRequestMapper).aUsuario(null);
	        verify(usuarioServicioPort).saveUser(null);
	        verifyNoInteractions(StringMessageResponse);
	    }

	    @Test
	    void testGuardarUsuario_ServicePortException() {
	        // Given
	        when(usuarioRequestMapper.aUsuario(userRequestDto)).thenReturn(user);
	        when(usuarioServicioPort.saveUser(user)).thenThrow(new RuntimeException("Service error"));

	        // When & Then
	        assertThrows(RuntimeException.class, () -> userHandler.saveUser(userRequestDto));
	        verify(usuarioRequestMapper).aUsuario(userRequestDto);
	        verify(usuarioServicioPort).saveUser(user);
	        verifyNoInteractions(StringMessageResponse);
	    }

	    @Test
	    void testGuardarUsuario_MapperException() {
	        // Given
	        when(usuarioRequestMapper.aUsuario(userRequestDto)).thenThrow(new RuntimeException("Mapping error"));

	        // When & Then
	        assertThrows(RuntimeException.class, () -> userHandler.saveUser(userRequestDto));
	        verify(usuarioRequestMapper).aUsuario(userRequestDto);
	        verifyNoInteractions(usuarioServicioPort);
	        verifyNoInteractions(StringMessageResponse);
	    }

	    @Test
	    void testGuardarUsuario_ResponseMapperException() {
	        // Given
	        when(usuarioRequestMapper.aUsuario(userRequestDto)).thenReturn(user);
	        when(usuarioServicioPort.saveUser(user)).thenReturn(messageResponse);
	      //  when(StringMessageResponse.toResponse(messageResponse)).thenThrow(new RuntimeException("Response mapping error"));

	        // When & Then
	        assertThrows(RuntimeException.class, () -> userHandler.saveUser(userRequestDto));
	        verify(usuarioRequestMapper).aUsuario(userRequestDto);
	        verify(usuarioServicioPort).saveUser(user);
	      //  verify(StringMessageResponse).toResponse(messageResponse);
	    }
}

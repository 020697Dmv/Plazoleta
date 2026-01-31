package com.plazoleta.domain.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.plazoleta.application.dto.request.LoginRequetDto;
import com.plazoleta.application.dto.response.AuthRespondeDto;
import com.plazoleta.domain.spi.ILoginPersistencePort;

@ExtendWith(MockitoExtension.class)
public class LoginUseCaseTest {

    @Mock
    private ILoginPersistencePort loginPersistencePort;

    @InjectMocks
    private LoginUseCase loginUseCase;

    @Test
    void login_shouldReturnAuthResponse() {
        LoginRequetDto request = new LoginRequetDto();
        request.setEmail("test@test.com");
        request.setPassword("password");

        AuthRespondeDto expectedResponse = new AuthRespondeDto();
        expectedResponse.setToken("test_token");

        when(loginPersistencePort.login(request)).thenReturn(expectedResponse);

        AuthRespondeDto actualResponse = loginUseCase.login(request);

        assertEquals(expectedResponse.getToken(), actualResponse.getToken());
    }
}

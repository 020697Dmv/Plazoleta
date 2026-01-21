package com.plazoleta.infrastructure.out.jpa.adapter;

import com.plazoleta.application.dto.request.LoginRequetDto;
import com.plazoleta.application.dto.response.AuthRespondeDto;
import com.plazoleta.infrastructure.out.jpa.auth.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginAdapterTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private LoginAdapter loginAdapter;

    @Test
    void login_delegatesToAuthService() {
        LoginRequetDto req = new LoginRequetDto();
        AuthRespondeDto resp = new AuthRespondeDto();

        when(authService.login(req)).thenReturn(resp);

        AuthRespondeDto result = loginAdapter.login(req);

        assertSame(resp, result);
        verify(authService).login(req);
    }
}

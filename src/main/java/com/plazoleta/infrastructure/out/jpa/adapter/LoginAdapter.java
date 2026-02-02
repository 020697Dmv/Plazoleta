package com.plazoleta.infrastructure.out.jpa.adapter;

import org.springframework.stereotype.Service;

import com.plazoleta.application.dto.request.LoginRequetDto;
import com.plazoleta.application.dto.response.AuthRespondeDto;
import com.plazoleta.domain.spi.ILoginPersistencePort;
import com.plazoleta.infrastructure.out.jpa.auth.AuthService;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LoginAdapter implements ILoginPersistencePort{
	
	private final AuthService authService;

	@Override
	public AuthRespondeDto login(LoginRequetDto request) {
		return authService.login(request);
	}

}

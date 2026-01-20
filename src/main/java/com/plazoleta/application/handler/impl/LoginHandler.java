package com.plazoleta.application.handler.impl;

import org.springframework.stereotype.Service;

import com.plazoleta.application.dto.request.LoginRequetDto;
import com.plazoleta.application.dto.response.AuthRespondeDto;
import com.plazoleta.application.handler.ILoginHandler;
import com.plazoleta.domain.api.ILoginServicePort;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class LoginHandler  implements ILoginHandler{
	
	private final ILoginServicePort LoginServicePort;

	@Override
	public AuthRespondeDto login(LoginRequetDto request) {
		AuthRespondeDto AuthRespondeDto=LoginServicePort.login(request);
		return AuthRespondeDto;
	}

}

package com.plazoleta.application.handler.impl;

import org.springframework.stereotype.Service;

import com.plazoleta.application.dto.request.LoginRequetDto;
import com.plazoleta.application.dto.response.AuthRespondeDto;
import com.plazoleta.application.handler.ILoginHandler;
import com.plazoleta.application.mapper.IMensaggeResponseMapper;
import com.plazoleta.application.mapper.IPlateRequestMapper;
import com.plazoleta.application.mapper.IPlateResponseMapper;
import com.plazoleta.domain.api.ILoginServicePort;
import com.plazoleta.domain.api.IPlateServicePort;

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

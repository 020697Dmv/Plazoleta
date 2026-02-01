package com.plazoleta.domain.usecase;

import org.springframework.stereotype.Service;

import com.plazoleta.application.dto.request.LoginRequetDto;
import com.plazoleta.application.dto.response.AuthRespondeDto;
import com.plazoleta.domain.api.ILoginServicePort;
import com.plazoleta.domain.spi.ILoginPersistencePort;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LoginUseCase  implements ILoginServicePort{
	
	
	private final ILoginPersistencePort loginPersistencePort;
	
	@Override
	public AuthRespondeDto login(LoginRequetDto request) {
		return loginPersistencePort.login(request);
	}

}

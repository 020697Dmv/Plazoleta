package com.plazoleta.application.handler.impl;

import com.plazoleta.application.dto.request.LoginRequetDto;
import com.plazoleta.application.dto.response.AuthRespondeDto;
import com.plazoleta.application.handler.ILoginHandler;
import com.plazoleta.domain.api.ILoginServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class LoginHandler  implements ILoginHandler{
	
	private final ILoginServicePort LoginServicePort;

	@Override
	public AuthRespondeDto login(LoginRequetDto request) {
		return LoginServicePort.login(request);
	}

}

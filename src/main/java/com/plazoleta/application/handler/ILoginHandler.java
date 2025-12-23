package com.plazoleta.application.handler;

import com.plazoleta.application.dto.request.LoginRequetDto;
import com.plazoleta.application.dto.response.AuthRespondeDto;

public interface ILoginHandler {
	AuthRespondeDto login(LoginRequetDto request);
}

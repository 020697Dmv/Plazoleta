package com.plazoleta.domain.api;

import com.plazoleta.application.dto.request.LoginRequetDto;
import com.plazoleta.application.dto.response.AuthRespondeDto;

public interface ILoginServicePort {

	AuthRespondeDto login(LoginRequetDto request);
}

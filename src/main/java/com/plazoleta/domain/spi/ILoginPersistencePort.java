package com.plazoleta.domain.spi;

import com.plazoleta.application.dto.request.LoginRequetDto;
import com.plazoleta.application.dto.response.AuthRespondeDto;

public interface ILoginPersistencePort {

	AuthRespondeDto login(LoginRequetDto request);
}

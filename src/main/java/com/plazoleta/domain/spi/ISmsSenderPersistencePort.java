package com.plazoleta.domain.spi;

import com.plazoleta.application.dto.request.SmsRequestDto;

public interface ISmsSenderPersistencePort {
	
	void sendSmd(SmsRequestDto smsrequest);

}

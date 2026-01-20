package com.plazoleta.domain.spi;

import com.plazoleta.domain.model.SmsRequest;

public interface ISmsSenderPersistencePort {
	
	void sendSmd(SmsRequest smsrequest);

}

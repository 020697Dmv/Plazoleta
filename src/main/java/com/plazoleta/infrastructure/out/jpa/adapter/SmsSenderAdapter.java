package com.plazoleta.infrastructure.out.jpa.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plazoleta.domain.model.SmsRequest;
import com.plazoleta.domain.spi.ISmsSenderPersistencePort;
import com.plazoleta.infrastructure.config.TwilioConfiguration;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class SmsSenderAdapter implements ISmsSenderPersistencePort{
	
    private static final Logger LOGGER = LoggerFactory.getLogger(SmsSenderAdapter.class);

    private final TwilioConfiguration twilioConfiguration;
    
    @Autowired
    public SmsSenderAdapter(TwilioConfiguration twilioConfiguration) {
		this.twilioConfiguration = twilioConfiguration;
	}

	@Override
	public void sendSmd(SmsRequest smsrequest) {
		
		 PhoneNumber to = new PhoneNumber(smsrequest.getPhoneNumber());
         PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
         String message = smsrequest.getMessage();
         MessageCreator creator = Message.creator(to, from, message);
         creator.create();
         LOGGER.info("Send sms {}", smsrequest);

	}



	

}

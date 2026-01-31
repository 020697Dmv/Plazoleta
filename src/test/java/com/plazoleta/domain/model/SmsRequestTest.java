package com.plazoleta.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SmsRequestTest {

    @Test
    void testSmsRequest() {
        String phoneNumber = "123456789";
        String message = "Test Message";

        SmsRequest smsRequest = new SmsRequest();
        smsRequest.setPhoneNumber(phoneNumber);
        smsRequest.setMessage(message);

        assertEquals(phoneNumber, smsRequest.getPhoneNumber());
        assertEquals(message, smsRequest.getMessage());

        SmsRequest smsRequest2 = new SmsRequest(phoneNumber, message);
        assertEquals(phoneNumber, smsRequest2.getPhoneNumber());
        assertEquals(message, smsRequest2.getMessage());
    }
}

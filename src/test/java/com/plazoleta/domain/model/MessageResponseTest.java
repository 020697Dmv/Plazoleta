package com.plazoleta.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MessageResponseTest {

    @Test
    void testMessageResponse() {
        String message = "Test Message";

        MessageResponse response = new MessageResponse();
        response.setMessage(message);

        assertEquals(message, response.getMessage());

        MessageResponse response2 = new MessageResponse(message);
        assertEquals(message, response2.getMessage());
    }
}

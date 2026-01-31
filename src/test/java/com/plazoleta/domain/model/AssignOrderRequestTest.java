package com.plazoleta.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AssignOrderRequestTest {

    @Test
    void testAssignOrderRequest() {
        Long orderId = 1L;
        PageRequest pageRequest = new PageRequest();

        AssignOrderRequest request = new AssignOrderRequest();
        request.setOrderId(orderId);
        request.setPageRequest(pageRequest);

        assertEquals(orderId, request.getOrderId());
        assertEquals(pageRequest, request.getPageRequest());

        AssignOrderRequest request2 = new AssignOrderRequest(orderId, pageRequest);
        assertEquals(orderId, request2.getOrderId());
        assertEquals(pageRequest, request2.getPageRequest());
    }
}

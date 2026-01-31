package com.plazoleta.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class OrderStatusRequestTest {

    @Test
    void testOrderStatusRequest() {
        String status = "PENDIENTE";
        Long idEmployee = 1L;
        PageRequest pageRequest = new PageRequest();

        OrderStatusRequest request = new OrderStatusRequest();
        request.setStatus(status);
        request.setIdEmployee(idEmployee);
        request.setPageRequest(pageRequest);

        assertEquals(status, request.getStatus());
        assertEquals(idEmployee, request.getIdEmployee());
        assertEquals(pageRequest, request.getPageRequest());

        OrderStatusRequest request2 = new OrderStatusRequest(status, idEmployee, pageRequest);
        assertEquals(status, request2.getStatus());
        assertEquals(idEmployee, request2.getIdEmployee());
        assertEquals(pageRequest, request2.getPageRequest());
    }
}

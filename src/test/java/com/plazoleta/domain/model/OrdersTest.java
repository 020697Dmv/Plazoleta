package com.plazoleta.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class OrdersTest {

    @Test
    void testOrders() {
        Long id = 1L;
        Long clientId = 2L;
        LocalDateTime date = LocalDateTime.now();
        String status = "PENDIENTE";
        Long nit = 123L;
        Long idEmpleado = 4L;

        Orders orders = new Orders();
        orders.setId(id);
        orders.setClientId(clientId);
        orders.setDate(date);
        orders.setStatus(status);
        orders.setNit(nit);
        orders.setIdEmpleado(idEmpleado);

        assertEquals(id, orders.getId());
        assertEquals(clientId, orders.getClientId());
        assertEquals(date, orders.getDate());
        assertEquals(status, orders.getStatus());
        assertEquals(nit, orders.getNit());
        assertEquals(idEmpleado, orders.getIdEmpleado());

        Orders orders2 = new Orders(id, clientId, date, status, nit, idEmpleado);
        assertEquals(id, orders2.getId());
        assertEquals(clientId, orders2.getClientId());
        assertEquals(date, orders2.getDate());
        assertEquals(status, orders2.getStatus());
        assertEquals(nit, orders2.getNit());
        assertEquals(idEmpleado, orders2.getIdEmpleado());
    }
}

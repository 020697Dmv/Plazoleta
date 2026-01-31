package com.plazoleta.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class OrderListModelTest {

    @Test
    void testOrderListModel() {
        Long id = 1L;
        Long clientId = 2L;
        LocalDateTime date = LocalDateTime.now();
        String status = "PENDIENTE";
        String restaurantName = "Test Restaurant";
        Long idEmpleado = 3L;
        List<OrderPlateDetails> details = new ArrayList<>();

        OrderListModel orderListModel = new OrderListModel();
        orderListModel.setId(id);
        orderListModel.setClientId(clientId);
        orderListModel.setDate(date);
        orderListModel.setStatus(status);
        orderListModel.setRestaurantName(restaurantName);
        orderListModel.setIdEmpleado(idEmpleado);
        orderListModel.setDetails(details);

        assertEquals(id, orderListModel.getId());
        assertEquals(clientId, orderListModel.getClientId());
        assertEquals(date, orderListModel.getDate());
        assertEquals(status, orderListModel.getStatus());
        assertEquals(restaurantName, orderListModel.getRestaurantName());
        assertEquals(idEmpleado, orderListModel.getIdEmpleado());
        assertEquals(details, orderListModel.getDetails());
    }
}

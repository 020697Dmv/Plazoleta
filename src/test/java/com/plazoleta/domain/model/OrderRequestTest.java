package com.plazoleta.domain.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderRequestTest {

    @Test
    void constructorAndGetters_work() {
        OrderPlateRequest plate = new OrderPlateRequest(5L, 2);
        OrderRequest req = new OrderRequest(3L, List.of(plate));

        assertEquals(3L, req.getRestaurantId());
        assertNotNull(req.getPlates());
        assertEquals(1, req.getPlates().size());
        assertEquals(5L, req.getPlates().get(0).getPlateId());
    }

}

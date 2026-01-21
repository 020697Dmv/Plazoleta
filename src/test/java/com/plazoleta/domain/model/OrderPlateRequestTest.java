package com.plazoleta.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderPlateRequestTest {

    @Test
    void gettersAndSetters_work() {
        OrderPlateRequest p = new OrderPlateRequest();
        p.setPlateId(7L);
        p.setQuantity(3);

        assertEquals(7L, p.getPlateId());
        assertEquals(3, p.getQuantity());
    }

}

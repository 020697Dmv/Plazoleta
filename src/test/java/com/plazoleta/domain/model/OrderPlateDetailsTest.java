package com.plazoleta.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class OrderPlateDetailsTest {

    @Test
    void testOrderPlateDetails() {
        String plateName = "Test Plate";
        Integer quantity = 2;

        OrderPlateDetails details = new OrderPlateDetails();
        details.setPlateName(plateName);
        details.setQuantity(quantity);

        assertEquals(plateName, details.getPlateName());
        assertEquals(quantity, details.getQuantity());
    }
}

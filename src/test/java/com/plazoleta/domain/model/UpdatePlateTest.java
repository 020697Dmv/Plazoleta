package com.plazoleta.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UpdatePlateTest {

    @Test
    void testUpdatePlate() {
        Long id = 1L;
        int price = 100;
        String description = "Test Description";

        UpdatePlate updatePlate = new UpdatePlate();
        updatePlate.setId(id);
        updatePlate.setPrice(price);
        updatePlate.setDescription(description);

        assertEquals(id, updatePlate.getId());
        assertEquals(price, updatePlate.getPrice());
        assertEquals(description, updatePlate.getDescription());

        UpdatePlate updatePlate2 = new UpdatePlate(id, price, description);
        assertEquals(id, updatePlate2.getId());
        assertEquals(price, updatePlate2.getPrice());
        assertEquals(description, updatePlate2.getDescription());
    }
}

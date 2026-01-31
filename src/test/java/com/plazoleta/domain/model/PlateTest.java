package com.plazoleta.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PlateTest {

    @Test
    void testPlate() {
        String namePlate = "Test Plate";
        int price = 100;
        String description = "Test Description";
        String urlImage = "http://test.com/image.png";
        String category = "Test Category";
        boolean active = true;
        Long restaurant = 1L;

        Plate plate = new Plate();
        plate.setNamePlate(namePlate);
        plate.setPrice(price);
        plate.setDescription(description);
        plate.setUrlImage(urlImage);
        plate.setCategory(category);
        plate.setActive(active);
        plate.setRestaurant(restaurant);

        assertEquals(namePlate, plate.getNamePlate());
        assertEquals(price, plate.getPrice());
        assertEquals(description, plate.getDescription());
        assertEquals(urlImage, plate.getUrlImage());
        assertEquals(category, plate.getCategory());
        assertTrue(plate.isActive());
        assertEquals(restaurant, plate.getRestaurant());

        Plate plate2 = new Plate(namePlate, price, description, urlImage, category, active, restaurant);
        assertEquals(namePlate, plate2.getNamePlate());
        assertEquals(price, plate2.getPrice());
        assertEquals(description, plate2.getDescription());
        assertEquals(urlImage, plate2.getUrlImage());
        assertEquals(category, plate2.getCategory());
        assertTrue(plate2.isActive());
        assertEquals(restaurant, plate2.getRestaurant());
    }
}

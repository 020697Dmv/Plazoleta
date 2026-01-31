package com.plazoleta.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RestaurantTest {

    @Test
    void testRestaurant() {
        Long nit = 1L;
        String name = "Test Restaurant";
        String address = "Test Address";
        String phone = "123456789";
        String urlLogo = "http://test.com/logo.png";
        Long identityDocumentOwner = 123L;

        Restaurant restaurant = new Restaurant();
        restaurant.setNit(nit);
        restaurant.setName(name);
        restaurant.setAddress(address);
        restaurant.setPhone(phone);
        restaurant.setUrlLogo(urlLogo);
        restaurant.setIdentity_document_owner(identityDocumentOwner);

        assertEquals(nit, restaurant.getNit());
        assertEquals(name, restaurant.getName());
        assertEquals(address, restaurant.getAddress());
        assertEquals(phone, restaurant.getPhone());
        assertEquals(urlLogo, restaurant.getUrlLogo());
        assertEquals(identityDocumentOwner, restaurant.getIdentity_document_owner());

        Restaurant restaurant2 = new Restaurant(nit, name, address, phone, urlLogo, identityDocumentOwner);
        assertEquals(nit, restaurant2.getNit());
        assertEquals(name, restaurant2.getName());
        assertEquals(address, restaurant2.getAddress());
        assertEquals(phone, restaurant2.getPhone());
        assertEquals(urlLogo, restaurant2.getUrlLogo());
        assertEquals(identityDocumentOwner, restaurant2.getIdentity_document_owner());
    }
}

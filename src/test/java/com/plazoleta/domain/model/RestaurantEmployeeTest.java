package com.plazoleta.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RestaurantEmployeeTest {

    @Test
    void testRestaurantEmployee() {
        Long id = 1L;
        Long idRestaurant = 2L;
        Long idEmployee = 3L;

        RestaurantEmployee employee = new RestaurantEmployee();
        employee.setId(id);
        employee.setIdRestaurant(idRestaurant);
        employee.setIdEmployee(idEmployee);

        assertEquals(id, employee.getId());
        assertEquals(idRestaurant, employee.getIdRestaurant());
        assertEquals(idEmployee, employee.getIdEmployee());

        RestaurantEmployee employee2 = new RestaurantEmployee(id, idRestaurant, idEmployee);
        assertEquals(id, employee2.getId());
        assertEquals(idRestaurant, employee2.getIdRestaurant());
        assertEquals(idEmployee, employee2.getIdEmployee());
    }
}

package com.plazoleta.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.plazoleta.application.dto.request.PageRequestDto;

public class SearchPlateTest {

    @Test
    void testSearchPlate() {
        Long idRestaurant = 1L;
        String category = "Test Category";
        PageRequestDto pageRequestDto = new PageRequestDto();

        SearchPlate searchPlate = new SearchPlate();
        searchPlate.setIdRestaurant(idRestaurant);
        searchPlate.setCategory(category);
        searchPlate.setPageRequestDto(pageRequestDto);

        assertEquals(idRestaurant, searchPlate.getIdRestaurant());
        assertEquals(category, searchPlate.getCategory());
        assertEquals(pageRequestDto, searchPlate.getPageRequestDto());

        SearchPlate searchPlate2 = new SearchPlate(idRestaurant, category, pageRequestDto);
        assertEquals(idRestaurant, searchPlate2.getIdRestaurant());
        assertEquals(category, searchPlate2.getCategory());
        assertEquals(pageRequestDto, searchPlate2.getPageRequestDto());
    }
}

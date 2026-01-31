package com.plazoleta.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PageRequestTest {

    @Test
    void testPageRequest() {
        Integer page = 1;
        Integer size = 10;

        PageRequest request = new PageRequest();
        request.setPage(page);
        request.setSize(size);

        assertEquals(page, request.getPage());
        assertEquals(size, request.getSize());

        PageRequest request2 = new PageRequest(page, size);
        assertEquals(page, request2.getPage());
        assertEquals(size, request2.getSize());
    }
}

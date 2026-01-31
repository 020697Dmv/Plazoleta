package com.plazoleta.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EnablePlateTest {

    @Test
    void testEnablePlate() {
        Long idPlate = 1L;
        Long identityDocumentOwner = 123L;
        Boolean active = true;

        EnablePlate enablePlate = new EnablePlate();
        enablePlate.setIdPlate(idPlate);
        enablePlate.setIdentityDocumentOwner(identityDocumentOwner);
        enablePlate.setActive(active);

        assertEquals(idPlate, enablePlate.getIdPlate());
        assertEquals(identityDocumentOwner, enablePlate.getIdentityDocumentOwner());
        assertTrue(enablePlate.getActive());

        EnablePlate enablePlate2 = new EnablePlate(idPlate, identityDocumentOwner, active);
        assertEquals(idPlate, enablePlate2.getIdPlate());
        assertEquals(identityDocumentOwner, enablePlate2.getIdentityDocumentOwner());
        assertTrue(enablePlate2.getActive());
    }
}

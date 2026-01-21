package com.plazoleta.domain.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.plazoleta.domain.model.EnablePlate;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.Plate;
import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.model.SearchPlate;
import com.plazoleta.domain.model.UpdatePlate;
import com.plazoleta.domain.spi.IPlatePersistencePort;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;

@ExtendWith(MockitoExtension.class)
public class PlateUseCaseTest {

    @Mock
    private IPlatePersistencePort platePersistencePort;

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @InjectMocks
    private PlateUseCase plateUseCase;

    @Test
    void savePlate_shouldReturnCreatedMessage() {
        Plate input = new Plate();
        input.setRestaurant(10L);
        input.setNamePlate("TestPlate");
        input.setPrice(50);
        input.setDescription("desc");

        Restaurant owner = new Restaurant();
        owner.setNit(10L);
        owner.setIdentity_document_owner(123L);

        Plate saved = new Plate();
        saved.setNamePlate("TestPlate");

        when(restaurantPersistencePort.findById(10L)).thenReturn(owner);
        when(platePersistencePort.savePlate(input, owner)).thenReturn(saved);

        MessageResponse resp = plateUseCase.savePlate(input);

        assertEquals("Plate created with name %TestPlate", resp.getMessage());
    }

    @Test
    void updatePlate_shouldUpdateAndReturnMessage() {
        UpdatePlate update = new UpdatePlate();
        update.setId(1L);
        update.setPrice(120);
        update.setDescription("updated");

        Plate existing = new Plate();
        existing.setNamePlate("OldName");
        existing.setPrice(100);
        existing.setDescription("old");

        Plate saved = new Plate();
        saved.setNamePlate("OldName");

        when(platePersistencePort.findyByIdEntity(1L)).thenReturn(existing);
        when(platePersistencePort.updatePlate(any(Plate.class))).thenReturn(saved);

        MessageResponse resp = plateUseCase.updatePlate(update);

        assertEquals("Plate update with name %OldName", resp.getMessage());
        verify(platePersistencePort).updatePlate(any(Plate.class));
    }

    @Test
    void updateActivePlate_whenOwner_shouldUpdateActiveAndReturnMessage() {
        EnablePlate enable = new EnablePlate();
        enable.setIdPlate(1L);
        enable.setIdentityDocumentOwner(123L);
        enable.setActive(true);

        Plate existing = new Plate();
        existing.setNamePlate("PlateA");
        existing.setRestaurant(20L);
        existing.setActive(false);

        Restaurant owner = new Restaurant();
        owner.setNit(20L);
        owner.setIdentity_document_owner(123L);

        Plate saved = new Plate();
        saved.setNamePlate("PlateA");
        saved.setActive(true);

        when(platePersistencePort.findyByIdEntity(1L)).thenReturn(existing);
        when(restaurantPersistencePort.findById(20L)).thenReturn(owner);
        when(platePersistencePort.updatePlate(any(Plate.class))).thenReturn(saved);

        MessageResponse resp = plateUseCase.updateActivePlate(enable);

        assertEquals("Plate update Active with name: PlateA", resp.getMessage());
        verify(platePersistencePort).updatePlate(any(Plate.class));
    }

    @Test
    void updateActivePlate_whenNotOwner_shouldReturnNotOwnerMessage() {
        EnablePlate enable = new EnablePlate();
        enable.setIdPlate(2L);
        enable.setIdentityDocumentOwner(999L);
        enable.setActive(true);

        Plate existing = new Plate();
        existing.setNamePlate("PlateB");
        existing.setRestaurant(30L);

        Restaurant owner = new Restaurant();
        owner.setNit(30L);
        owner.setIdentity_document_owner(123L);

        when(platePersistencePort.findyByIdEntity(2L)).thenReturn(existing);
        when(restaurantPersistencePort.findById(30L)).thenReturn(owner);

        MessageResponse resp = plateUseCase.updateActivePlate(enable);

        assertEquals("The user is not the owner of the restaurant this plate belongs to", resp.getMessage());
    }

    @Test
    void toResponseList_shouldReturnListFromPersistence() {
        SearchPlate search = new SearchPlate();
        Plate p = new Plate();
        p.setNamePlate("P1");
        List<Plate> list = Collections.singletonList(p);

        when(platePersistencePort.toResponseList(search)).thenReturn(list);

        List<Plate> result = plateUseCase.toResponseList(search);

        assertEquals(1, result.size());
        assertEquals("P1", result.get(0).getNamePlate());
    }
}

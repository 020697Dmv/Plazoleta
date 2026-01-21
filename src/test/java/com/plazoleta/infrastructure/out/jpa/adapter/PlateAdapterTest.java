package com.plazoleta.infrastructure.out.jpa.adapter;

import com.plazoleta.domain.model.Plate;
import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.model.SearchPlate;
import com.plazoleta.application.dto.request.PageRequestDto;
import com.plazoleta.infrastructure.exception.PlateNotFoundException;
import com.plazoleta.infrastructure.out.jpa.entity.PlateEntity;
import com.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
import com.plazoleta.infrastructure.out.jpa.mapper.IPlateEntityMapper;
import com.plazoleta.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.plazoleta.infrastructure.out.jpa.repository.IPlateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlateAdapterTest {

    @Mock
    private IPlateRepository plateRepository;

    @Mock
    private IPlateEntityMapper plateEntityMapper;

    @Mock
    private IRestaurantEntityMapper restaurantEntityMapper;

    @InjectMocks
    private PlateAdapter plateAdapter;

    @Test
    void savePlate_setsRestaurantAndSaves() {
        Plate plate = new Plate();
        Restaurant rest = new Restaurant();
        PlateEntity entity = new PlateEntity();
        PlateEntity saved = new PlateEntity();
        Plate out = new Plate();

        when(restaurantEntityMapper.toEntity(rest)).thenReturn(new RestaurantEntity());
        when(plateEntityMapper.toEntity(plate)).thenReturn(entity);
        when(plateRepository.save(entity)).thenReturn(saved);
        when(plateEntityMapper.toPlate(saved)).thenReturn(out);

        Plate result = plateAdapter.savePlate(plate, rest);

        assertSame(out, result);
        verify(restaurantEntityMapper).toEntity(rest);
        verify(plateEntityMapper).toEntity(plate);
        verify(plateRepository).save(entity);
        verify(plateEntityMapper).toPlate(saved);
    }

    @Test
    void findyById_present_maps() {
        PlateEntity entity = new PlateEntity();
        Plate out = new Plate();
        when(plateRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(plateEntityMapper.toPlate(entity)).thenReturn(out);

        Optional<Plate> result = plateAdapter.findyById(1L);
        assertTrue(result.isPresent());
        assertSame(out, result.get());
    }

    @Test
    void updatePlate_savesAndReturns() {
        Plate plate = new Plate();
        PlateEntity entity = new PlateEntity();
        PlateEntity saved = new PlateEntity();
        Plate out = new Plate();

        when(plateEntityMapper.toEntity(plate)).thenReturn(entity);
        when(plateRepository.save(entity)).thenReturn(saved);
        when(plateEntityMapper.toPlate(saved)).thenReturn(out);

        Plate result = plateAdapter.updatePlate(plate);

        assertSame(out, result);
    }

    @Test
    void findyByIdEntity_missing_throws() {
        when(plateRepository.findById(9L)).thenReturn(Optional.empty());
        assertThrows(PlateNotFoundException.class, () -> plateAdapter.findyByIdEntity(9L));
    }

    @Test
    void toResponseList_noCategory_usesFindByRestaurantNit() {
        SearchPlate sp = new SearchPlate();
        sp.setIdRestaurant(1L);
        sp.setCategory(null);
        PageRequestDto pr = new PageRequestDto();
        pr.setPage(0);
        pr.setSize(10);
        sp.setPageRequestDto(pr);

        PlateEntity pEntity = new PlateEntity();
        List<PlateEntity> content = List.of(pEntity);
        Page<PlateEntity> page = new PageImpl<>(content);

        when(plateRepository.findByRestaurantNit(eq(1L), any())).thenReturn(page);
        when(plateEntityMapper.toPlate(pEntity)).thenReturn(new Plate());

        List<Plate> result = plateAdapter.toResponseList(sp);
        assertEquals(1, result.size());
    }

    @Test
    void toResponseList_empty_throws() {
        SearchPlate sp = new SearchPlate();
        sp.setIdRestaurant(1L);
        sp.setCategory(null);
        PageRequestDto pr = new PageRequestDto();
        pr.setPage(0);
        pr.setSize(10);
        sp.setPageRequestDto(pr);

        Page<PlateEntity> empty = Page.empty();
        when(plateRepository.findByRestaurantNit(eq(1L), any())).thenReturn(empty);
        assertThrows(PlateNotFoundException.class, () -> plateAdapter.toResponseList(sp));
    }
}

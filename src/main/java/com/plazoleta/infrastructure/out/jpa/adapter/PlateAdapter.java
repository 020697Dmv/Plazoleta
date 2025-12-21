package com.plazoleta.infrastructure.out.jpa.adapter;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.plazoleta.application.dto.request.UpdatePlateRequestDto;
import com.plazoleta.domain.model.Plate;
import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.spi.IPlatePersistencePort;
import com.plazoleta.infrastructure.out.jpa.entity.PlateEntity;
import com.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
import com.plazoleta.infrastructure.out.jpa.mapper.IPlateEntityMapper;
import com.plazoleta.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.plazoleta.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.plazoleta.infrastructure.out.jpa.repository.IPlateRepository;
import com.plazoleta.infrastructure.out.jpa.repository.IRestaurantRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PlateAdapter implements IPlatePersistencePort {

	private final IPlateRepository plateRepository;

	private final IPlateEntityMapper plateEntityMapper;

	private final IRestaurantEntityMapper restaurantEntityMapper;

	@Override
	public Plate savePlate(Plate plate, Restaurant restaurant) {
		RestaurantEntity restaurantEntity = restaurantEntityMapper.toEntity(restaurant);
		PlateEntity plateEntity = plateEntityMapper.toEntity(plate);
		plateEntity.setRestaurant(restaurantEntity);
		PlateEntity saveEntity = plateRepository.save(plateEntity);
		return plateEntityMapper.toPlate(saveEntity);
	}

	@Override
	public Optional<Plate> findyById(Long id) {
		return plateRepository.findById(id).map(plateEntityMapper::toPlate);
	}

	@Override
	public Plate updatePlate(PlateEntity plate) {
		PlateEntity saveEntity = plateRepository.save(plate);
		return plateEntityMapper.toPlate(saveEntity);
	}

	@Override
	public Optional<PlateEntity> findyByIdEntity(Long id) {
		return plateRepository.findById(id);
	}


}

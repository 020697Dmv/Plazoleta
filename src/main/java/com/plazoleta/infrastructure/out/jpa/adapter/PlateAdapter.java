package com.plazoleta.infrastructure.out.jpa.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


import com.plazoleta.domain.model.Plate;
import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.model.SearchPlate;
import com.plazoleta.domain.spi.IPlatePersistencePort;
import com.plazoleta.infrastructure.exception.PlateNotFoundException;
import com.plazoleta.infrastructure.out.jpa.entity.PlateEntity;
import com.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
import com.plazoleta.infrastructure.out.jpa.mapper.IPlateEntityMapper;
import com.plazoleta.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.plazoleta.infrastructure.out.jpa.repository.IPlateRepository;

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
	public Plate updatePlate(Plate plate) {
		PlateEntity entityToSave = plateEntityMapper.toEntity(plate);
		PlateEntity saveEntity = plateRepository.save(entityToSave);
		return plateEntityMapper.toPlate(saveEntity);
	}

	@Override
	public Plate findyByIdEntity(Long id) {
		
		Optional<PlateEntity>plate=plateRepository.findById(id);
		if(plate.isEmpty()) {
			throw new PlateNotFoundException();
		}
		
		return plateEntityMapper.toPlate(plate.get());
	}

	@Override
	public List<Plate> toResponseList(SearchPlate searchPlate) {
		
		Pageable pageableResponse = PageRequest.of(searchPlate.getPageRequestDto().getPage(), searchPlate.getPageRequestDto().getSize());
		
		Page<PlateEntity> platePage;
		if(searchPlate.getCategory() == null ||searchPlate.getCategory().isEmpty()) {
		  
			platePage= plateRepository.findByRestaurantNit(searchPlate.getIdRestaurant(), pageableResponse);
		} else {
			
			 
			platePage=  plateRepository.findByRestaurantNitAndCategory(searchPlate.getIdRestaurant()
					 ,searchPlate.getCategory(),pageableResponse);
		}
		
		if (platePage.isEmpty()) {
	        throw new PlateNotFoundException();
	    }
		
		return platePage.map(plateEntityMapper::toPlate).getContent();
		
	}

	@Override
	public Optional<PlateEntity> findById(Long id) {
		return plateRepository.findById(id);

	}


}

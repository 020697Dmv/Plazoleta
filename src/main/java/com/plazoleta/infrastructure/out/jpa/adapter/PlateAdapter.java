package com.plazoleta.infrastructure.out.jpa.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.plazoleta.application.dto.request.SearchPlateRequestDto;
import com.plazoleta.application.dto.request.UpdatePlateRequestDto;
import com.plazoleta.domain.model.Plate;
import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.spi.IPlatePersistencePort;
import com.plazoleta.infrastructure.exception.PlateNotFoundException;
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

	@Override
	public List<Plate> toResponseList(SearchPlateRequestDto searchPlateRequestDto) {
		
		Pageable pageableResponse = PageRequest.of(searchPlateRequestDto.getPageRequestDto().getPage(), searchPlateRequestDto.getPageRequestDto().getSize());
		
		Page<PlateEntity> platePage;
		if(searchPlateRequestDto.getCategory() == null ||searchPlateRequestDto.getCategory().isEmpty()) {
		  
			platePage= plateRepository.findByRestaurantNit(searchPlateRequestDto.getIdRestaurant(), pageableResponse);
		} else {
			
			 
			platePage=  plateRepository.findByRestaurantNitAndCategory(searchPlateRequestDto.getIdRestaurant()
					 ,searchPlateRequestDto.getCategory(),pageableResponse);
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

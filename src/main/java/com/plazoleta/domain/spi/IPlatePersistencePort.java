package com.plazoleta.domain.spi;

import java.util.List;
import java.util.Optional;

import com.plazoleta.application.dto.request.SearchPlateRequestDto;
import com.plazoleta.application.dto.request.UpdatePlateRequestDto;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.Plate;
import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.infrastructure.out.jpa.entity.PlateEntity;

public interface IPlatePersistencePort {

	Plate savePlate(Plate plate, Restaurant restaurant);
	
	Optional<Plate> findyById(Long id);
	
	Plate updatePlate(PlateEntity plate);
	
	Optional<PlateEntity> findyByIdEntity(Long id);
	
	List<Plate> toResponseList(SearchPlateRequestDto searchPlateRequestDto);
	
	Optional<PlateEntity> findById(Long id);

}

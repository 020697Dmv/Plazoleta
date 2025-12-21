package com.plazoleta.domain.spi;

import java.util.Optional;

import com.plazoleta.application.dto.request.UpdatePlateRequestDto;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.Plate;
import com.plazoleta.domain.model.Restaurant;

public interface IPlatePersistencePort {

	Plate savePlate(Plate plate, Restaurant restaurant);
	
	Optional<Plate> findyById(Long id);
	

}

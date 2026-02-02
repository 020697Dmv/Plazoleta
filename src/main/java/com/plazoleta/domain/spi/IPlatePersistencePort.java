package com.plazoleta.domain.spi;

import com.plazoleta.domain.model.Plate;
import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.model.SearchPlate;
import com.plazoleta.infrastructure.out.jpa.entity.PlateEntity;

import java.util.List;
import java.util.Optional;

public interface IPlatePersistencePort {

	Plate savePlate(Plate plate, Restaurant restaurant);
	
	Optional<Plate> findyById(Long id);
	
	Plate updatePlate(Plate plate);
	
	Plate findyByIdEntity(Long id);
	
	List<Plate> toResponseList(SearchPlate searchPlate);
	
	Optional<PlateEntity> findById(Long id);

}

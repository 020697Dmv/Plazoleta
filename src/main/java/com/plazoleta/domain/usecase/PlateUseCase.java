package com.plazoleta.domain.usecase;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.plazoleta.application.dto.request.UpdatePlateRequestDto;
import com.plazoleta.domain.api.IPlateServicePort;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.Plate;
import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.spi.IPlatePersistencePort;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.domain.spi.IUserPersistencePort;
import com.plazoleta.domain.validacion.PlateValidation;
import com.plazoleta.infrastructure.exception.PlateNotFoundException;
import com.plazoleta.infrastructure.exception.RestaurantNotFoundException;
import com.plazoleta.infrastructure.out.jpa.entity.PlateEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PlateUseCase implements IPlateServicePort{
	
	private final IPlatePersistencePort platePersistencePort;
	
	private final IRestaurantPersistencePort restaurantPersistencePort;

	@Override
	public MessageResponse savePlate(Plate plate) {
		
		Restaurant restaurantOwner=restaurantPersistencePort
				.findById(plate.getRestaurant()).orElseThrow(RestaurantNotFoundException::new);
		PlateValidation.ValidatePlate(plate);
		Plate plateSave=platePersistencePort.savePlate(plate, restaurantOwner);
		return new MessageResponse(
	            "Plate created with name %"+ plateSave.getNamePlate());
	}

	@Override
	public MessageResponse updatePlate(UpdatePlateRequestDto updatePlateRequestDto) {
		PlateEntity plateObject=platePersistencePort.findyByIdEntity(updatePlateRequestDto.getId())
				.orElseThrow(PlateNotFoundException::new);
		plateObject.setPrice(updatePlateRequestDto.getPrice());
		plateObject.setDescription(updatePlateRequestDto.getDescription());
		PlateValidation.ValidatePlateEntity(plateObject);
		Plate plateSave=platePersistencePort.updatePlate(plateObject);
		return new MessageResponse(
	            "Plate update with name %"+ plateSave.getNamePlate());
	}

}

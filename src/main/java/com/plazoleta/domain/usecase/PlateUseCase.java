package com.plazoleta.domain.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.plazoleta.application.dto.request.EnablePlateResquestDto;
import com.plazoleta.application.dto.request.SearchPlateRequestDto;
import com.plazoleta.domain.api.IPlateServicePort;
import com.plazoleta.domain.model.EnablePlate;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.Plate;
import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.model.UpdatePlate;
import com.plazoleta.domain.spi.IPlatePersistencePort;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.domain.validacion.PlateValidation;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PlateUseCase implements IPlateServicePort{
	
	private final IPlatePersistencePort platePersistencePort;
	
	private final IRestaurantPersistencePort restaurantPersistencePort;

	@Override
	public MessageResponse savePlate(Plate plate) {
		
		Restaurant restaurantOwner=restaurantPersistencePort
				.findById(plate.getRestaurant());
		PlateValidation.ValidatePlate(plate);
		Plate plateSave=platePersistencePort.savePlate(plate, restaurantOwner);
		return new MessageResponse(
	            "Plate created with name %"+ plateSave.getNamePlate());
	}

	@Override
	public MessageResponse updatePlate(UpdatePlate updatePlate) {
		Plate plateObject=platePersistencePort.findyByIdEntity(updatePlate.getId());
		plateObject.setPrice(updatePlate.getPrice());
		plateObject.setDescription(updatePlate.getDescription());
		PlateValidation.ValidatePlateEntity(plateObject);
		Plate plateSave=platePersistencePort.updatePlate(plateObject);
		return new MessageResponse(
	            "Plate update with name %"+ plateSave.getNamePlate());
	}

	@Override
	public MessageResponse updateActivePlate(EnablePlate enablePlateo) {
		
	    Plate plateObject = platePersistencePort.findyByIdEntity(enablePlateo.getIdPlate());
	    
	    Long idRestaurant=plateObject.getRestaurant();
	    
	    Restaurant restaurantOwner = restaurantPersistencePort
	            .findById(idRestaurant);

	
	    
	    if (enablePlateo.getIdentityDocumentOwner().equals(restaurantOwner.getIdentity_document_owner())) {
	        plateObject.setActive(enablePlateo.getActive());
	        
	        Plate plateSave = platePersistencePort.updatePlate(plateObject);
	        
	        return new MessageResponse(
	                String.format("Plate update Active with name: %s", plateSave.getNamePlate()));
	    } else {
	    	return new MessageResponse(
	                String.format("The user is not the owner of the restaurant this plate belongs to",plateObject.getNamePlate()));
	    }
	}

	@Override
	public List<Plate> toResponseList(SearchPlateRequestDto searchPlateRequestDto) {

		return platePersistencePort.toResponseList(searchPlateRequestDto);
	}

}

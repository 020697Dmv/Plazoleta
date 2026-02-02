package com.plazoleta.domain.api;

import com.plazoleta.domain.model.*;

import java.util.List;

public interface IPlateServicePort {
	
	MessageResponse savePlate(Plate plate);

	MessageResponse updatePlate(UpdatePlate updatePlate);

	MessageResponse updateActivePlate(EnablePlate enablePlate);
	
	List<Plate> toResponseList(SearchPlate searchPlate);

}

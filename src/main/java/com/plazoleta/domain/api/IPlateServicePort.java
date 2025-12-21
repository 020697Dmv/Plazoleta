package com.plazoleta.domain.api;

import com.plazoleta.application.dto.request.UpdatePlateRequestDto;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.Plate;

public interface IPlateServicePort {
	
	MessageResponse savePlate(Plate plate);

	MessageResponse updatePlate(UpdatePlateRequestDto updatePlateRequestDto);

}

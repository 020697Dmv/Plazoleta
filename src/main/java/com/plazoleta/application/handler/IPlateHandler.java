package com.plazoleta.application.handler;

import com.plazoleta.application.dto.request.EnablePlateResquestDto;
import com.plazoleta.application.dto.request.PlateRequestDto;
import com.plazoleta.application.dto.request.UpdatePlateRequestDto;
import com.plazoleta.domain.model.MessageResponse;

public interface IPlateHandler {
	
	MessageResponse savePlate(PlateRequestDto plateRequestDto);

	MessageResponse updatePlate(UpdatePlateRequestDto updatePlateRequestDto);
	
	MessageResponse updateActivePlate(EnablePlateResquestDto enablePlateResquestDto);

}

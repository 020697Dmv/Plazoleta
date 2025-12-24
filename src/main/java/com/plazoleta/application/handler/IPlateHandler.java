package com.plazoleta.application.handler;

import java.util.List;

import com.plazoleta.application.dto.request.EnablePlateResquestDto;
import com.plazoleta.application.dto.request.PlateRequestDto;
import com.plazoleta.application.dto.request.SearchPlateRequestDto;
import com.plazoleta.application.dto.request.UpdatePlateRequestDto;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.Plate;

public interface IPlateHandler {
	
	MessageResponse savePlate(PlateRequestDto plateRequestDto);

	MessageResponse updatePlate(UpdatePlateRequestDto updatePlateRequestDto);
	
	MessageResponse updateActivePlate(EnablePlateResquestDto enablePlateResquestDto);
	
	List<Plate> toResponseListPlates(SearchPlateRequestDto searchPlateRequestDto);
}

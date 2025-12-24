package com.plazoleta.domain.api;

import java.util.List;

import com.plazoleta.application.dto.request.EnablePlateResquestDto;
import com.plazoleta.application.dto.request.SearchPlateRequestDto;
import com.plazoleta.application.dto.request.UpdatePlateRequestDto;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.Plate;

public interface IPlateServicePort {
	
	MessageResponse savePlate(Plate plate);

	MessageResponse updatePlate(UpdatePlateRequestDto updatePlateRequestDto);

	MessageResponse updateActivePlate(EnablePlateResquestDto enablePlateResquestDto);
	
	List<Plate> toResponseList(SearchPlateRequestDto searchPlateRequestDto);

}

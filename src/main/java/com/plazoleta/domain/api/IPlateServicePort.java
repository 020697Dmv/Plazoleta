package com.plazoleta.domain.api;

import java.util.List;

import com.plazoleta.application.dto.request.EnablePlateResquestDto;
import com.plazoleta.application.dto.request.SearchPlateRequestDto;
import com.plazoleta.application.dto.request.UpdatePlateRequestDto;
import com.plazoleta.domain.model.EnablePlate;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.Plate;
import com.plazoleta.domain.model.UpdatePlate;

public interface IPlateServicePort {
	
	MessageResponse savePlate(Plate plate);

	MessageResponse updatePlate(UpdatePlate updatePlate);

	MessageResponse updateActivePlate(EnablePlate enablePlate);
	
	List<Plate> toResponseList(SearchPlateRequestDto searchPlateRequestDto);

}

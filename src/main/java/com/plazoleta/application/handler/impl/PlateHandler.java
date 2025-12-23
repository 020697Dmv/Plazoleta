package com.plazoleta.application.handler.impl;

import org.springframework.stereotype.Service;

import com.plazoleta.application.dto.request.EnablePlateResquestDto;
import com.plazoleta.application.dto.request.PlateRequestDto;
import com.plazoleta.application.dto.request.UpdatePlateRequestDto;
import com.plazoleta.application.handler.IPlateHandler;
import com.plazoleta.application.mapper.IMensaggeResponseMapper;
import com.plazoleta.application.mapper.IPlateRequestMapper;
import com.plazoleta.application.mapper.IPlateResponseMapper;
import com.plazoleta.application.mapper.IRestaurantRequestMapper;
import com.plazoleta.application.mapper.IRestaurantResponseMapper;
import com.plazoleta.domain.api.IPlateServicePort;
import com.plazoleta.domain.api.IRestaurantServicePort;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.Plate;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PlateHandler implements IPlateHandler{
	
	private final IPlateServicePort plateServicePort;
	private final IPlateRequestMapper plateRequestMapper;
	private final IPlateResponseMapper plateResponseMapper;
	private final IMensaggeResponseMapper StringMessageResponse;

	
	@Override
	public MessageResponse savePlate(PlateRequestDto plateRequestDto) {

		Plate plate=plateRequestMapper.toPlate(plateRequestDto);
		MessageResponse messageResponse=plateServicePort.savePlate(plate);
		return messageResponse;
	}


	@Override
	public MessageResponse updatePlate(UpdatePlateRequestDto updatePlateRequestDto) {
		MessageResponse messageResponse=plateServicePort.updatePlate(updatePlateRequestDto);
		return messageResponse;
	}


	@Override
	public MessageResponse updateActivePlate(EnablePlateResquestDto enablePlateResquestDto) {
		MessageResponse messageResponse=plateServicePort.updateActivePlate(enablePlateResquestDto);
		return messageResponse;
	}
	
	

}

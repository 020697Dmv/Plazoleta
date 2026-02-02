package com.plazoleta.application.handler.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.plazoleta.application.dto.request.EnablePlateResquestDto;
import com.plazoleta.application.dto.request.PlateRequestDto;
import com.plazoleta.application.dto.request.SearchPlateRequestDto;
import com.plazoleta.application.dto.request.UpdatePlateRequestDto;
import com.plazoleta.application.handler.IPlateHandler;
import com.plazoleta.application.mapper.IPlateRequestMapper;
import com.plazoleta.application.mapper.IPlateResponseMapper;
import com.plazoleta.domain.api.IPlateServicePort;
import com.plazoleta.domain.model.EnablePlate;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.Plate;
import com.plazoleta.domain.model.SearchPlate;
import com.plazoleta.domain.model.UpdatePlate;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PlateHandler implements IPlateHandler{
	
	private final IPlateServicePort plateServicePort;
	private final IPlateRequestMapper plateRequestMapper;
	private final IPlateResponseMapper plateResponseMapper;

	
	@Override
	public MessageResponse savePlate(PlateRequestDto plateRequestDto) {
		Plate plate=plateRequestMapper.toPlate(plateRequestDto);
		return plateServicePort.savePlate(plate);
	}


	@Override
	public MessageResponse updatePlate(UpdatePlateRequestDto updatePlateRequestDto) {
		UpdatePlate updatePlate=plateResponseMapper.toUpdatePlate(updatePlateRequestDto);
		return plateServicePort.updatePlate(updatePlate);
	}


	@Override
	public MessageResponse updateActivePlate(EnablePlateResquestDto enablePlateResquestDto) {
		EnablePlate enablePlate=plateResponseMapper.toEnablePlate(enablePlateResquestDto);
		return plateServicePort.updateActivePlate(enablePlate);
	}


	@Override
	public List<Plate> toResponseListPlates(SearchPlateRequestDto searchPlateRequestDto) {
		SearchPlate searchPlate=plateResponseMapper.searchPlate(searchPlateRequestDto);
		return plateServicePort.toResponseList(searchPlate);
	}
	
	

}

package com.plazoleta.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.plazoleta.application.dto.request.EnablePlateResquestDto;
import com.plazoleta.application.dto.request.UpdatePlateRequestDto;
import com.plazoleta.application.dto.response.PlateResponseDto;
import com.plazoleta.domain.model.EnablePlate;
import com.plazoleta.domain.model.Plate;
import com.plazoleta.domain.model.UpdatePlate;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlateResponseMapper {
	
	PlateResponseDto toPlate(Plate plate);
	
	UpdatePlate toUpdatePlate(UpdatePlateRequestDto UpdatePlateRequestDto);
	
	EnablePlate toEnablePlate(EnablePlateResquestDto enablePlateResquestDto);

}

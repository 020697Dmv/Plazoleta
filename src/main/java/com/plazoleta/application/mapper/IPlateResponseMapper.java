package com.plazoleta.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.plazoleta.application.dto.response.PlateResponseDto;
import com.plazoleta.domain.model.Plate;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlateResponseMapper {
	
	PlateResponseDto toPlate(Plate plate);
}

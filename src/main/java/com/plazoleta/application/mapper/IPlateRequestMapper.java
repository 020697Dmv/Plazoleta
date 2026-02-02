package com.plazoleta.application.mapper;

import com.plazoleta.application.dto.request.PlateRequestDto;
import com.plazoleta.domain.model.Plate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlateRequestMapper {
	
	Plate toPlate(PlateRequestDto plateRequestDto);

}

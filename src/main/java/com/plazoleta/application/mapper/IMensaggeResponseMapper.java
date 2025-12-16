package com.plazoleta.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.plazoleta.application.dto.response.StringResponseDto;
import com.plazoleta.domain.model.MessageResponse;



@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IMensaggeResponseMapper {
	StringResponseDto toResponse(MessageResponse messageResponse);

}

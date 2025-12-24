package com.plazoleta.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.plazoleta.application.dto.request.ClientResquestDto;
import com.plazoleta.domain.model.Client;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IClientResquestMapper {
	
	Client toClient(ClientResquestDto clientResquestDto);

}

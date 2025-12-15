package com.plazoleta.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.plazoleta.application.dto.response.UserRespuestaDto;
import com.plazoleta.domain.model.User;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUsuarioResponseMapper {
	
	UserRespuestaDto toRespuesta(User usuario); 
	

}

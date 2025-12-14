package com.plazoleta.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.plazoleta.application.dto.request.UsuarioRequesteDto;
import com.plazoleta.domain.model.Usuario;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUsuarioRequestMapper {
	
	Usuario aUsuario(UsuarioRequesteDto usuarioRequesteDto);

}

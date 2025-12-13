package com.plazoleta.infrastructure.out.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.plazoleta.domain.model.Usuario;

import com.plazoleta.infrastructure.out.jpa.entity.UsuarioEntidad;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUsuarioEntidadMapeo {
		
	UsuarioEntidad cambioEntidad(Usuario usuario);
	
	Usuario cambioUsuario(UsuarioEntidad usuarioEntidad);
}

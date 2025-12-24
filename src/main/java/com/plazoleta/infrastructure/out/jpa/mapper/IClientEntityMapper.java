package com.plazoleta.infrastructure.out.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.plazoleta.domain.model.Client;
import com.plazoleta.infrastructure.out.jpa.entity.ClientEntity;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IClientEntityMapper {
	
	ClientEntity toEntity(Client client);
	
	Client toClient(ClientEntity clientEntity);

}

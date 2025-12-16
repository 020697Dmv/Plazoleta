package com.plazoleta.infrastructure.out.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.plazoleta.domain.model.User;

import com.plazoleta.infrastructure.out.jpa.entity.UserEntity;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserEntityMapper {
		
	UserEntity toEntity(User usuario);
	
	User toUser(UserEntity userEntity);
}

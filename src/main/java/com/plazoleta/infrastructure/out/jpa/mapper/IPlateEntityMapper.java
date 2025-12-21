package com.plazoleta.infrastructure.out.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.plazoleta.domain.model.Plate;
import com.plazoleta.infrastructure.out.jpa.entity.PlateEntity;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlateEntityMapper {
	
	@Mapping(target = "restaurant", ignore = true)
	PlateEntity  toEntity(Plate plate);
	
    @Mapping(source = "restaurant.nit", target = "restaurant")
	Plate toPlate(PlateEntity plateEntity);
}

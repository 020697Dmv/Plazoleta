package com.plazoleta.infrastructure.out.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.plazoleta.domain.model.Employee;
import com.plazoleta.infrastructure.out.jpa.entity.EmployeeEntity;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IEmployeeEntityMapper {
	
	EmployeeEntity toEntity(Employee employee);
	
	Employee toEmployee(EmployeeEntity employeeEntity);

}

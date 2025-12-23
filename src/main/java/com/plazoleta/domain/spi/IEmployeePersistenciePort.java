package com.plazoleta.domain.spi;

import java.util.Optional;

import com.plazoleta.domain.model.Employee;

public interface IEmployeePersistenciePort {

	Employee saveEmployee(Employee employee);
	
	Optional<Employee>  findById(Long id);

}

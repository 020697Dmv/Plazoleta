package com.plazoleta.domain.usecase;

import org.springframework.stereotype.Service;

import com.plazoleta.domain.api.IEmployeeServicePort;
import com.plazoleta.domain.model.Employee;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.spi.IEmployeePersistenciePort;
import com.plazoleta.domain.spi.IPlatePersistencePort;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.infrastructure.exception.EmployeeAlreadyExistException;
import com.plazoleta.infrastructure.exception.EmployeeNotFoundException;
import com.plazoleta.infrastructure.exception.RestaurantNotFoundException;
import com.plazoleta.infrastructure.exception.UserAlreadyExistException;
import com.plazoleta.infrastructure.out.jpa.util.Role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployeeUseCase  implements IEmployeeServicePort{
	
	private final IEmployeePersistenciePort employeePersistenciePort;
	
	@Override
	public MessageResponse saveEmployee(Employee employee) {
		
		if(employeePersistenciePort.findById(employee.getId()).isPresent()) {
			throw new EmployeeAlreadyExistException();
		}
		employee.setRole(Role.EMPLOYEE);
		Employee saveEmployee=employeePersistenciePort.saveEmployee(employee);
        return new MessageResponse(String.format("Employee created with id %d", saveEmployee.getId()));
	}
	
	

}

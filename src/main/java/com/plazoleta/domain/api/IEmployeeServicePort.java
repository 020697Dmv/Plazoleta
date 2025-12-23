package com.plazoleta.domain.api;

import com.plazoleta.domain.model.Employee;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.User;

public interface IEmployeeServicePort {
		
	MessageResponse saveEmployee(Employee employee);
}

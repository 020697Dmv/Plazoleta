package com.plazoleta.application.handler;

import com.plazoleta.application.dto.request.EmployeeRequestDto;
import com.plazoleta.application.dto.request.UserRequesteDto;
import com.plazoleta.domain.model.MessageResponse;

public interface IEmployeeHandler {
	
	MessageResponse saveEmployee(EmployeeRequestDto employeeRequestDto);


}

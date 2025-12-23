package com.plazoleta.application.handler.impl;

import org.springframework.stereotype.Service;

import com.plazoleta.application.dto.request.EmployeeRequestDto;
import com.plazoleta.application.handler.IEmployeeHandler;
import com.plazoleta.application.mapper.IEmployeeRequestMapper;
import com.plazoleta.application.mapper.IMensaggeResponseMapper;
import com.plazoleta.application.mapper.IUserRequestMapper;
import com.plazoleta.application.mapper.IUserResponseMapper;
import com.plazoleta.domain.api.IEmployeeServicePort;
import com.plazoleta.domain.api.IUserServicePort;
import com.plazoleta.domain.model.Employee;
import com.plazoleta.domain.model.MessageResponse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeHandler implements IEmployeeHandler{
	
	public final IEmployeeServicePort employeeServicePort;
	public final IEmployeeRequestMapper employeeRequestMapper;
	
	@Override
	public MessageResponse saveEmployee(EmployeeRequestDto employeeRequestDto) {
		Employee employee=employeeRequestMapper.toEmployee(employeeRequestDto);
		MessageResponse messageResponse=employeeServicePort.saveEmployee(employee);
		return messageResponse;
	}
	

}

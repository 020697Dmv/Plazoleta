package com.plazoleta.infrastructure.out.jpa.adapter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.plazoleta.domain.model.Employee;
import com.plazoleta.domain.spi.IEmployeePersistenciePort;
import com.plazoleta.infrastructure.out.jpa.entity.EmployeeEntity;
import com.plazoleta.infrastructure.out.jpa.mapper.IEmployeeEntityMapper;
import com.plazoleta.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.plazoleta.infrastructure.out.jpa.repository.IEmployeeRepository;
import com.plazoleta.infrastructure.out.jpa.repository.IUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployeeAdapter implements IEmployeePersistenciePort{
	
	private final IEmployeeRepository employeeRepository;
	
	private final IEmployeeEntityMapper employeeEntityMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Employee saveEmployee(Employee employee) {
		employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		EmployeeEntity employeeEntity=employeeRepository.save(employeeEntityMapper.toEntity(employee));
		return employeeEntityMapper.toEmployee(employeeEntity);
	}

	@Override
	public Optional<Employee> findById(Long id) {
		return employeeRepository.findById(id).map(employeeEntityMapper::toEmployee);
	}
	
	

}

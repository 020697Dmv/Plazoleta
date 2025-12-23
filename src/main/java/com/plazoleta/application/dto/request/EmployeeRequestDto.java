package com.plazoleta.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequestDto {
	
	private Long id;
	
	private String name;
	
	private String lastname;
	
	private String phone;

	private String email;
	
	private String password;

}

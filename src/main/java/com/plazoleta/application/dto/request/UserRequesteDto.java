package com.plazoleta.application.dto.request;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequesteDto {
	
	private Long id;
	
	private String name;
	
	private String lastname;
	
	private String phone;

	private LocalDate birthdate;
	
	private String email;
	
	private String password;
	


}

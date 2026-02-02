package com.plazoleta.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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
	
	private int role;
	
	private Long idRestaurant;

}

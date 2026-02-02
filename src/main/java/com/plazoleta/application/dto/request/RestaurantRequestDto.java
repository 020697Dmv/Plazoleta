package com.plazoleta.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantRequestDto {
	
	private Long nit;
	
	private String name;
	
	private String address;
	
	private String phone;
	
	private String urlLogo;

	private Long identity_document_owner;

}

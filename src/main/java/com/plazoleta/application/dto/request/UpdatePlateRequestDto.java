package com.plazoleta.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePlateRequestDto {
	
	private Long id;
	
	private int price;
	
	private String description;

}

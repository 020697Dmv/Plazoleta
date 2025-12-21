package com.plazoleta.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlateRequestDto {
	
	private String namePlate;
	
	private int price;
	
	private String description;
	
	private String urlImage;
	
	private String category;
	
    private boolean active;

    private Long restaurant;
}

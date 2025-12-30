package com.plazoleta.application.dto.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {

	private Long idClient;
	private Long restaurantId; 
    private List<OrderPlateRequestDto> plates;
}

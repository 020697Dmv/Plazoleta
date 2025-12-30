package com.plazoleta.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderPlateRequestDto {
	
	private Long plateId;
    private Integer quantity;

}

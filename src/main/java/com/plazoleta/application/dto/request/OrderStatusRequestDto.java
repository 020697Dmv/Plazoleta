package com.plazoleta.application.dto.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderStatusRequestDto {
	
	String status;
	
	Long idEmployee;

	PageRequestDto pageRequestDto;


}

package com.plazoleta.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnablePlateResquestDto {
	
	private Long idPlate;
	
	private Long identityDocumentOwner;
	
	private Boolean active;

}

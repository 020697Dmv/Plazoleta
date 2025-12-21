package com.plazoleta.domain.validacion;

import com.plazoleta.domain.model.Plate;
import static com.plazoleta.domain.validacion.ValidationUtils.requeridoPriceValido;

public class PlateValidation {
	
	private PlateValidation() {
		
	}
	
	public static void ValidatePlate(final Plate plate) {
		
		requeridoPriceValido(plate.getPrice(),"Plate");
	}

}

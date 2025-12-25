package com.plazoleta.domain.validacion;

import com.plazoleta.domain.model.User;
import com.plazoleta.infrastructure.out.jpa.util.Role;

import static com.plazoleta.domain.validacion.ValidationUtils.requeridoNoNull;
import static com.plazoleta.domain.validacion.ValidationUtils.requeridoEmailValido;
import static com.plazoleta.domain.validacion.ValidationUtils.requeridoTelefonoValido;
import static com.plazoleta.domain.validacion.ValidationUtils.requeridoDocumentoValido;
import static com.plazoleta.domain.validacion.ValidationUtils.requeridoMayorDeEdad;



public class UserValidation {

	
	private UserValidation() {
		
	}
	
	public static void validateUser(final User usuario) {
		
		requeridoNoNull(usuario,"usuario");
		requeridoEmailValido(usuario.getEmail(),"Correo");
		requeridoTelefonoValido(usuario.getPhone(),"Celular");
		requeridoDocumentoValido(usuario.getId(),"Id");
		if(usuario.getRole().equals(Role.ADMINISTRATOR)) {
			requeridoMayorDeEdad(usuario.getBirthdate(),"Fecha de nacimiento");
		}
		
	}
}

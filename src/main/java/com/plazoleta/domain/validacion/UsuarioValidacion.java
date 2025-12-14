package com.plazoleta.domain.validacion;

import com.plazoleta.domain.model.Usuario;

import static com.plazoleta.domain.validacion.ValidacionUtilidades.requeridoNoNull;
import static com.plazoleta.domain.validacion.ValidacionUtilidades.requeridoEmailValido;
import static com.plazoleta.domain.validacion.ValidacionUtilidades.requeridoTelefonoValido;
import static com.plazoleta.domain.validacion.ValidacionUtilidades.requeridoDocumentoValido;
import static com.plazoleta.domain.validacion.ValidacionUtilidades.requeridoMayorDeEdad;



public class UsuarioValidacion {

	
	private UsuarioValidacion() {
		
	}
	
	public static void validarUsuario(final Usuario usuario) {
		
		requeridoNoNull(usuario,"usuario");
		requeridoEmailValido(usuario.getCorreo(),"Correo");
		requeridoTelefonoValido(usuario.getCelular(),"Celular");
		requeridoDocumentoValido(usuario.getId(),"Id");
		requeridoMayorDeEdad(usuario.getFechaNacimiento(),"Fecha de nacimiento");
		
	}
}

package com.plazoleta.domain.validacion;

import com.plazoleta.domain.exception.DominionExcepcion;
import com.plazoleta.infrastructure.excepcion.UsuarioExisteExcepcion;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.Period;

public class ValidacionUtilidades {
	
	private ValidacionUtilidades() {
		
	}
	
	private static final String EMAIL_PATRON =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	
	private static final String TELEFONO_PATRON =
            "^\\+?[0-9]{1,12}$";

    private static final String DOCUMENTO_PATRON =
            "^[0-9]+$";
	
    private static final Pattern emailPatron = Pattern.compile(EMAIL_PATRON);
    
    private static final Pattern TELEFONO_PATTERN =
            Pattern.compile(TELEFONO_PATRON);

    private static final Pattern DOCUMENTO_PATTERN =
            Pattern.compile(DOCUMENTO_PATRON);

	
	public static <T> T requeridoNoNull(T obj, String field) {
        if (obj == null) {
            throw new DominionExcepcion(field + " No puede ser nulo");
        }
        return obj;
    }
	
	 public static String requeridoEmailValido(String email, String field) {
	        if (email == null || email.trim().isEmpty()) {
	            throw new DominionExcepcion(field + " no puede ser vacío");
	        }
	        if (!emailPatron.matcher(email).matches()) {
	            throw new DominionExcepcion(field + " no tiene un formato válido");
	        }
	        return email;
	    }
	 
	
	 public static String requeridoTelefonoValido(String telefono, String field) {
	        if (telefono == null || telefono.trim().isEmpty()) {
	            throw new DominionExcepcion(field + " no puede ser vacío");
	        }
	        if (telefono.length() > 13) {
	            throw new DominionExcepcion(field + " no puede tener más de 13 caracteres");
	        }
	        if (!TELEFONO_PATTERN.matcher(telefono).matches()) {
	            throw new DominionExcepcion(field + " tiene un formato inválido");
	        }
	        return telefono;
	    }

	 public static Long requeridoDocumentoValido(Long documento, String field) {
		    if (documento == null) {
		        throw new DominionExcepcion(field + " no puede ser nulo");
		    }
		    if (documento <= 0) {
		        throw new DominionExcepcion(field + " debe ser un número positivo");
		    }
		    return documento;
		}

	 
	 public static LocalDate requeridoMayorDeEdad(LocalDate fechaNacimiento, String field) {
		    if (fechaNacimiento == null) {
		        throw new DominionExcepcion(field + " no puede ser nulo");
		    }

		    int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();

		    if (edad < 18) {
		        throw new DominionExcepcion("El usuario debe ser mayor de edad");
		    }

		    return fechaNacimiento;
		}



}

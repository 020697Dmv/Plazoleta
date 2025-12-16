package com.plazoleta.domain.validacion;

import com.plazoleta.domain.exception.DomainExcepcion;
import com.plazoleta.infrastructure.excepcion.UserExistExcepcion;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.Period;

public class ValidationUtils {
	
	private ValidationUtils() {
		
	}
	
	private static final String EMAIL_PATRON =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	
	private static final String TELEFONO_PATRON =
            "^\\+?[0-9]{1,12}$";

    private static final String DOCUMENTO_PATRON =
            "^[0-9]+$";
    
    private static final String NOMBRE_RESTAURANTE_PATRON =
            "^(?=.*[a-zA-Z])[a-zA-Z0-9\\s]+$";
    
    private static final Pattern name_valid =
            Pattern.compile(NOMBRE_RESTAURANTE_PATRON);
    
    private static final Pattern document_valid =
            Pattern.compile(DOCUMENTO_PATRON);
	
    private static final Pattern emailPatron = Pattern.compile(EMAIL_PATRON);
    
    private static final Pattern TELEFONO_PATTERN =
            Pattern.compile(TELEFONO_PATRON);

    

	
	public static <T> T requeridoNoNull(T obj, String field) {
        if (obj == null) {
            throw new DomainExcepcion(field + " No puede ser nulo");
        }
        return obj;
    }
	
	 public static String requeridoEmailValido(String email, String field) {
	        if (email == null || email.trim().isEmpty()) {
	            throw new DomainExcepcion(field + " no puede ser vacío");
	        }
	        if (!emailPatron.matcher(email).matches()) {
	            throw new DomainExcepcion(field + " no tiene un formato válido");
	        }
	        return email;
	    }
	 
	 public static Long requiredValidarNit(Long nit, String nombreCampo) {
	        
	        if (nit == null) {
	            throw new DomainExcepcion(nombreCampo + " es requerido.");
	        }
	        
	        String nitString = String.valueOf(nit);
	        
	        if (!document_valid.matcher(nitString).matches()) {
	            throw new DomainExcepcion(nombreCampo + " no tiene un formato numérico válido (solo dígitos positivos).");
	        }
	        
	        return nit;
	    }
	 
	
	 public static String requeridoTelefonoValido(String telefono, String field) {
	        if (telefono == null || telefono.trim().isEmpty()) {
	            throw new DomainExcepcion(field + " no puede ser vacío");
	        }
	        if (telefono.length() > 13) {
	            throw new DomainExcepcion(field + " no puede tener más de 13 caracteres");
	        }
	        if (!TELEFONO_PATTERN.matcher(telefono).matches()) {
	            throw new DomainExcepcion(field + " tiene un formato inválido");
	        }
	        return telefono;
	    }

	 public static Long requeridoDocumentoValido(Long documento, String field) {
		    if (documento == null) {
		        throw new DomainExcepcion(field + " no puede ser nulo");
		    }
		    if (documento <= 0) {
		        throw new DomainExcepcion(field + " debe ser un número positivo");
		    }
		    return documento;
		}

	 
	 public static LocalDate requeridoMayorDeEdad(LocalDate fechaNacimiento, String field) {
		    if (fechaNacimiento == null) {
		        throw new DomainExcepcion(field + " no puede ser nulo");
		    }

		    int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();

		    if (edad < 18) {
		        throw new DomainExcepcion("El usuario debe ser mayor de edad");
		    }

		    return fechaNacimiento;
		}

	 public static String requiredValidarNombre(String name, String nombreCampo) {
	        
	        if (name == null || name.trim().isEmpty()) {
	            throw new DomainExcepcion(nombreCampo + " es requerido.");
	        }
	        
	        if (!name_valid.matcher(name).matches()) {
	            throw new DomainExcepcion(nombreCampo + " no es válido. Debe contener letras y no puede ser solo números.");
	        }
	        
	        return name;
	    }

}

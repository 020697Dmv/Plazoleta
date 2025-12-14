package com.plazoleta.application.dto.request;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequesteDto {
	
private Long id;
	
	private String nombre;
	
	private String apellido;
	
	private String celular;

	private LocalDate fechaNacimiento;
	
	private String correo;
	
	private String clave;

}

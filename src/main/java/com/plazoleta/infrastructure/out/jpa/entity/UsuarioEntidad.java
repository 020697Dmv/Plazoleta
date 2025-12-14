package com.plazoleta.infrastructure.out.jpa.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class UsuarioEntidad {
	
	@Id
	@Column(name="documento_identidad")
	private Long id;
	
	@Column()
	private String nombre;
	
	@Column()
	private String apellido;
	
	@Column()
	private String celular;

	@Column(name="fecha_nacimiento")
	private LocalDate fechaNacimiento;
	
	@Column()
	private String correo;
	
	@Column()
	private String clave;
	
	@Column()
	private String rol;
}

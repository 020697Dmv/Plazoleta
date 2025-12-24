package com.plazoleta.infrastructure.out.jpa.entity;

import com.plazoleta.infrastructure.out.jpa.util.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client")
public class ClientEntity {

	
	@Id
	@Column(name="identity_document")
	private Long id;
	
	private String name;
	
	private String lastname;
	
	private String phone;

	private String email;
	
	private String password;
	
	@Column()
	@Enumerated(EnumType.STRING) 
	private Role role;
}

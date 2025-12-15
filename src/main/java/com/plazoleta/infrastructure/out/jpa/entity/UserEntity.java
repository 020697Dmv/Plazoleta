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
@Table(name = "user")
public class UserEntity {
	
	@Id
	@Column(name="identity_document")
	private Long id;
	
	@Column()
	private String name;
	
	@Column()
	private String lastname;
	
	@Column()
	private String phone;

	@Column(name="birthdate")
	private LocalDate birthdate;
	
	@Column()
	private String email;
	
	@Column()
	private String password;
	
	@Column()
	private String role;
}

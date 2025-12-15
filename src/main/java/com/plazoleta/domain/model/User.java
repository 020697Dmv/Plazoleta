package com.plazoleta.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import java.time.LocalDate;

public class User {
	
	
	private Long id;
	
	private String name;
	
	private String lastname;
	
	private String phone;

	private LocalDate birthdate;
	
	private String email;
	
	private String password;
	
	private String role;
	
	

	public User() {
		
	}

	

	public User(Long id, String name, String lastname, String phone, LocalDate birthdate, String email,
			String password, String role) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.phone = phone;
		this.birthdate = birthdate;
		this.email = email;
		this.password = password;
		this.role = role;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getLastname() {
		return lastname;
	}



	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public LocalDate getBirthdate() {
		return birthdate;
	}



	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}

	
	
	

}

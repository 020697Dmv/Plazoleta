package com.plazoleta.domain.model;

import com.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
import com.plazoleta.infrastructure.out.jpa.entity.UserEntity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class RestaurantEmployee {
	
	
	private Long id;
		
    private Long idRestaurant;

    private Long idEmployee;

	public RestaurantEmployee() {
		super();
	}

	public RestaurantEmployee(Long id, Long idRestaurant, Long idEmployee) {
		super();
		this.id = id;
		this.idRestaurant = idRestaurant;
		this.idEmployee = idEmployee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdRestaurant() {
		return idRestaurant;
	}

	public void setIdRestaurant(Long idRestaurant) {
		this.idRestaurant = idRestaurant;
	}

	public Long getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(Long idEmployee) {
		this.idEmployee = idEmployee;
	}
	
    
}

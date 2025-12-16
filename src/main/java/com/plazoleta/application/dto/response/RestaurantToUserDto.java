package com.plazoleta.application.dto.response;

import com.plazoleta.infrastructure.out.jpa.entity.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantToUserDto {
	
	
	private Long nit;
	
	private String name;
	
	private String address;
	
	private String phone;
	
	private String urlLogo;

    private UserEntity propietario;
}

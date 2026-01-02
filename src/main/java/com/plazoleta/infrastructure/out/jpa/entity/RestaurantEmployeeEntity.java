package com.plazoleta.infrastructure.out.jpa.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "restaurant_employee")
public class RestaurantEmployeeEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	
	@ManyToOne
    @JoinColumn(name = "restaurant_nit", referencedColumnName = "nit", nullable = false)
    private RestaurantEntity restaurant;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "identity_document", nullable = false)
    private UserEntity employee;

}

package com.plazoleta.infrastructure.out.jpa.entity;

import jakarta.persistence.*;
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

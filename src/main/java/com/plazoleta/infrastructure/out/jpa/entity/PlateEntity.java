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
@Table(name = "plate")
public class PlateEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="name_plate")
	private String namePlate;
	
	private int price;
	
	private String description;
	
	@Column(name = "url_image")
	private String urlImage;
	
	private String category;
	
	@Column(name = "activo")
    private boolean active;
	
	@ManyToOne
    @JoinColumn(name = "identity_restaurant_owner", referencedColumnName = "nit")
    private RestaurantEntity restaurant;
}

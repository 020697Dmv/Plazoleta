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
@Table(name = "restaurant")
public class RestaurantEntity {
	
	@Id
	@Column(name="nit")
	private Long nit;
	
	@Column()
	private String name;
	
	@Column()
	private String address;
	
	@Column()
	private String phone;
	
	@Column(name="url_logo")
	private String urlLogo;

	@ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(
        name = "identity_document_owner", 
        referencedColumnName = "identity_document", 
        nullable = false)
    private UserEntity propietario;
}

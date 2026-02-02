package com.plazoleta.infrastructure.out.jpa.entity;

import java.time.LocalDateTime;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "status", nullable = false)
    private String status; // 'PENDIENTE', 'EN_PREPARACION', etc.

    @ManyToOne
    @JoinColumn(name = "fk_restaurant_owner", referencedColumnName = "nit")
    private RestaurantEntity restaurant;
    
    @Column(name = "fk_employee_id") 
    private Long fkEmployeeId;
        
    @Column(name = "security_pin") 
    private String securityPin;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderPlateEntity> orderPlates;

}

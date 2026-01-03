package com.plazoleta.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderListModel {
    private Long id;
    private Long clientId;
    private LocalDateTime date;
    private String status;
    private String restaurantName; 
    private Long idEmpleado;
    private List<OrderPlateDetails> details; 
}

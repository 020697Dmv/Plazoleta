package com.plazoleta.domain.model;

import java.util.List;

public class OrderRequest {

	private Long restaurantId;	
	private List<OrderPlateRequest>plates;
	
	public OrderRequest() {
		
	}

	public OrderRequest(Long restaurantId, List<OrderPlateRequest> plates) {
		super();
		this.restaurantId = restaurantId;
		this.plates = plates;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public List<OrderPlateRequest> getPlates() {
		return plates;
	}

	public void setPlates(List<OrderPlateRequest> plates) {
		this.plates = plates;
	}
	
	
}

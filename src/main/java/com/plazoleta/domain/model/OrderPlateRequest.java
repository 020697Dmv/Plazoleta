package com.plazoleta.domain.model;

public class OrderPlateRequest {
	
	private Long plateId;
    private Integer quantity;
    
    public OrderPlateRequest() {
    	
    }

	public OrderPlateRequest(Long plateId, Integer quantity) {
		super();
		this.plateId = plateId;
		this.quantity = quantity;
	}

	public Long getPlateId() {
		return plateId;
	}

	public void setPlateId(Long plateId) {
		this.plateId = plateId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
    
    

}

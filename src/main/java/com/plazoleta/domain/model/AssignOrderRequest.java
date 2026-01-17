package com.plazoleta.domain.model;

public class AssignOrderRequest {
	 Long orderId;
	 
	 PageRequest pageRequest;
	 
	 public AssignOrderRequest() {
		 
	 }

	public AssignOrderRequest(Long orderId, PageRequest pageRequest) {
		super();
		this.orderId = orderId;
		this.pageRequest = pageRequest;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public PageRequest getPageRequest() {
		return pageRequest;
	}

	public void setPageRequest(PageRequest pageRequest) {
		this.pageRequest = pageRequest;
	}
	 
	 

}

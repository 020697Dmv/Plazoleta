package com.plazoleta.domain.model;

public class OrderStatusRequest {
	
	String status;
	
	Long idEmployee;

	PageRequest pageRequest;

	public OrderStatusRequest(String status, Long idEmployee, PageRequest pageRequest) {
		super();
		this.status = status;
		this.idEmployee = idEmployee;
		this.pageRequest = pageRequest;
	}
	
	public OrderStatusRequest() {
		
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(Long idEmployee) {
		this.idEmployee = idEmployee;
	}

	public PageRequest getPageRequest() {
		return pageRequest;
	}

	public void setPageRequest(PageRequest pageRequest) {
		this.pageRequest = pageRequest;
	}
	
	
}

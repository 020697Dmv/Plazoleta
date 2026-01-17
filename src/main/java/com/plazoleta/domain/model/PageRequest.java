package com.plazoleta.domain.model;

public class PageRequest {
	private Integer page;	
	private Integer size;
	
	public PageRequest() {
		
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public PageRequest(Integer page, Integer size) {
		super();
		this.page = page;
		this.size = size;
	}
	
	
}

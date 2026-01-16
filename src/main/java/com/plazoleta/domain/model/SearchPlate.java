package com.plazoleta.domain.model;

import com.plazoleta.application.dto.request.PageRequestDto;

public class SearchPlate {
	
	private Long idRestaurant;	
	private String category;	
	private PageRequestDto pageRequestDto;
	
	public SearchPlate() {
			
	}

	public SearchPlate(Long idRestaurant, String category, PageRequestDto pageRequestDto) {
		super();
		this.idRestaurant = idRestaurant;
		this.category = category;
		this.pageRequestDto = pageRequestDto;
	}

	public Long getIdRestaurant() {
		return idRestaurant;
	}

	public void setIdRestaurant(Long idRestaurant) {
		this.idRestaurant = idRestaurant;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public PageRequestDto getPageRequestDto() {
		return pageRequestDto;
	}

	public void setPageRequestDto(PageRequestDto pageRequestDto) {
		this.pageRequestDto = pageRequestDto;
	}
	
	

}

package com.plazoleta.domain.model;

public class UpdatePlate {

	private Long id;	
	private int price;	
	private String description;
	
	public UpdatePlate() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UpdatePlate(Long id, int price, String description) {
		super();
		this.id = id;
		this.price = price;
		this.description = description;
	}
	
	
	
}

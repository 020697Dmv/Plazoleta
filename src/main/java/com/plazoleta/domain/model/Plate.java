package com.plazoleta.domain.model;

public class Plate {
	
	private String namePlate;
	
	private int price;
	
	private String description;
	
	private String urlImage;
	
	private String category;
	
    private boolean active;

    private Long restaurant;
    
    public Plate() {
    	
    }
    

	public Plate(String namePlate, int price, String description, String urlImage, String category, boolean active,
			Long restaurant) {
		super();
		this.namePlate = namePlate;
		this.price = price;
		this.description = description;
		this.urlImage = urlImage;
		this.category = category;
		this.active = active;
		this.restaurant = restaurant;
	}



	public String getNamePlate() {
		return namePlate;
	}

	public void setNamePlate(String namePlate) {
		this.namePlate = namePlate;
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

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Long getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Long restaurant) {
		this.restaurant = restaurant;
	}
    
    
}

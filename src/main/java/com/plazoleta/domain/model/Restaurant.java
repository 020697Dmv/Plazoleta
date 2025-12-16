package com.plazoleta.domain.model;

public class Restaurant {
	
	private Long nit;
	
	private String name;
	
	private String address;
	
	private String phone;
	
	private String urlLogo;

	private Long identity_document_owner;

	public Restaurant() {
	}
	
	public Restaurant(Long nit, String name, String address, String phone, String urlLogo,
			Long identity_document_owner) {
		super();
		this.nit = nit;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.urlLogo = urlLogo;
		this.identity_document_owner = identity_document_owner;
	}



	public Long getNit() {
		return nit;
	}

	public void setNit(Long nit) {
		this.nit = nit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUrlLogo() {
		return urlLogo;
	}

	public void setUrlLogo(String urlLogo) {
		this.urlLogo = urlLogo;
	}

	public Long getIdentity_document_owner() {
		return identity_document_owner;
	}

	public void setIdentity_document_owner(Long identity_document_owner) {
		this.identity_document_owner = identity_document_owner;
	}

	

	
	
}

package com.plazoleta.domain.model;

public class EnablePlate {

	private Long idPlate;	
	private Long identityDocumentOwner;	
	private Boolean active;
	
	public EnablePlate() {
		
	}

	public EnablePlate(Long idPlate, Long identityDocumentOwner, Boolean active) {
		super();
		this.idPlate = idPlate;
		this.identityDocumentOwner = identityDocumentOwner;
		this.active = active;
	}

	public Long getIdPlate() {
		return idPlate;
	}

	public void setIdPlate(Long idPlate) {
		this.idPlate = idPlate;
	}

	public Long getIdentityDocumentOwner() {
		return identityDocumentOwner;
	}

	public void setIdentityDocumentOwner(Long identityDocumentOwner) {
		this.identityDocumentOwner = identityDocumentOwner;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
}

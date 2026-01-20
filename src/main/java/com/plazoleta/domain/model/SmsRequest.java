package com.plazoleta.domain.model;

public class SmsRequest {
	String phoneNumber;
	String message;
	
	public SmsRequest() {
		
	}

	public SmsRequest(String phoneNumber, String message) {
		super();
		this.phoneNumber = phoneNumber;
		this.message = message;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}

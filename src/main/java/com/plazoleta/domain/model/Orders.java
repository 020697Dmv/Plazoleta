package com.plazoleta.domain.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Orders {
	
	    private Long id;

	    private Long clientId;

	    private LocalDateTime date;

	    private String status;

		public Orders() {
			super();
		}
		
		

		public Orders(Long id, Long clientId, LocalDateTime date, String status) {
			super();
			this.id = id;
			this.clientId = clientId;
			this.date = date;
			this.status = status;
		}



		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getClientId() {
			return clientId;
		}

		public void setClientId(Long clientId) {
			this.clientId = clientId;
		}

		public LocalDateTime getDate() {
			return date;
		}

		public void setDate(LocalDateTime date) {
			this.date = date;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		} 
	    
	    
}

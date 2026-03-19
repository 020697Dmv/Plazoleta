package com.plazoleta.infrastructure.out.http.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.plazoleta.infrastructure.out.http.mapper.MapperProduct;
import com.plazoleta.infrastructure.out.http.model.ProductRecord;

@Service
public class ProductService implements IProductService{
	
	
	private RestTemplate restTemplate= new RestTemplate();
	
	private HttpHeaders httpHeaders = new HttpHeaders();
	
	
	private MapperProduct mapper;
	
	public ProductService() {
		this.httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		}

	@Override
	public List<ProductRecord> products() {
		ResponseEntity<JsonNode> responseEntity =
	            restTemplate.getForEntity(
	                    "https://fakestoreapi.com/products",
	                    JsonNode.class);

	    if (responseEntity.getStatusCode() == HttpStatus.OK) {
	        JsonNode productosNode = responseEntity.getBody();
	        if (productosNode != null) {
	            return mapper.mappingToProductosDto(productosNode);
	        }
	    }

	    return Collections.emptyList();
	}

}

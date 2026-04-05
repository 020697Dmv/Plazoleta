package com.plazoleta.infrastructure.out.http.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.plazoleta.infrastructure.out.http.model.ProductRecord;

@Component
public class MapperProduct {
	
	
	public List<ProductRecord> mappingToProductosDto(JsonNode datos) {
	    List<ProductRecord> productos = new ArrayList<>();

	    if (datos.isArray()) {
	        for (JsonNode element : datos) {
	        	ProductRecord productoDto = new ProductRecord(
	                element.get("id").asLong(),
	                element.get("title").asText(),
	                element.get("price").asDouble(),
	                element.get("description").asText(),
	                element.get("category").asText(),
	                element.get("image").asText()
	            );
	            productos.add(productoDto);
	        }
	    }

	    return productos;
	}

}

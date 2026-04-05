package com.plazoleta.domain.api;

import java.util.List;

import com.plazoleta.infrastructure.out.http.model.ProductRecord;

public interface IProductServicePort {
	
	List<ProductRecord> productsList();
}

package com.plazoleta.application.handler;

import java.util.List;

import com.plazoleta.infrastructure.out.http.model.ProductRecord;

public interface IProductsHandler {
	
	List<ProductRecord> productsList();

}

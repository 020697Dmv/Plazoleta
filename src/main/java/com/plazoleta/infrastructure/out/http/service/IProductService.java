package com.plazoleta.infrastructure.out.http.service;

import java.util.List;

import com.plazoleta.infrastructure.out.http.model.ProductRecord;


public interface IProductService {
	
	List<ProductRecord> products();

}

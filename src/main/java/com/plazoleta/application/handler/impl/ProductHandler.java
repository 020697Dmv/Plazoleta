package com.plazoleta.application.handler.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.plazoleta.application.handler.IProductsHandler;
import com.plazoleta.application.mapper.IOrderRequestMapper;
import com.plazoleta.domain.api.IOrderServicePort;
import com.plazoleta.domain.api.IProductServicePort;
import com.plazoleta.infrastructure.out.http.model.ProductRecord;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductHandler implements IProductsHandler {
	
	private final IProductServicePort productServicePort;
	
	@Override
	public List<ProductRecord> productsList() {
		
		return productServicePort.productsList();
	}

}

package com.plazoleta.infrastructure.out.jpa.adapter;

import java.util.List;

import org.springframework.stereotype.Service;

import com.plazoleta.domain.spi.IProductPersistencePort;
import com.plazoleta.infrastructure.out.http.model.ProductRecord;
import com.plazoleta.infrastructure.out.http.service.IProductService;
import com.plazoleta.infrastructure.out.jpa.auth.AuthService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductAdapter implements IProductPersistencePort{
	
	
	private final IProductService productService;
	
	@Override
	public List<ProductRecord> productsList() {
		return productService.products();
	}
	

}

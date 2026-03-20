package com.plazoleta.domain.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.plazoleta.domain.api.IProductServicePort;
import com.plazoleta.domain.spi.IPlatePersistencePort;
import com.plazoleta.domain.spi.IProductPersistencePort;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.infrastructure.out.http.model.ProductRecord;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductUseCase implements IProductServicePort{
	
	private final IProductPersistencePort productPersistencePort;

	@Override
	public List<ProductRecord> productsList() {

		return productPersistencePort.productsList();
	}

}

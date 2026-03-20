package com.plazoleta.domain.spi;

import java.util.List;

import com.plazoleta.infrastructure.out.http.model.ProductRecord;

public interface IProductPersistencePort {
	List<ProductRecord> productsList();
}

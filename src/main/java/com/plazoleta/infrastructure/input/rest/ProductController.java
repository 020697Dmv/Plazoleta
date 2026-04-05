package com.plazoleta.infrastructure.input.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plazoleta.application.handler.IOrderHandler;
import com.plazoleta.application.handler.IProductsHandler;
import com.plazoleta.infrastructure.out.http.model.ProductRecord;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/Products")
@RequiredArgsConstructor
public class ProductController {
	
	private final IProductsHandler productsHandler;
	
	@Operation(summary = "getProductos", description = "Servicio para obtener todod los productos")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Exitoso"),
		@ApiResponse(responseCode = "204", description = "No hay información"),
		@ApiResponse(responseCode = "500", description = "Error interno"),
		@ApiResponse(responseCode = "400", description = "Error de request"),
		@ApiResponse(responseCode = "401", description = "No autorizado")})
	@GetMapping(value = "/productos", produces = "application/json")
	public List<ProductRecord> getProductos() throws IOException {

		return this.productsHandler.productsList();
	}

}

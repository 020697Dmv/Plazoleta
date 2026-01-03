package com.plazoleta.infrastructure.input.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.plazoleta.application.dto.request.LoginRequetDto;
import com.plazoleta.application.dto.request.OrderRequestDto;
import com.plazoleta.application.dto.request.OrderStatusRequestDto;
import com.plazoleta.application.dto.request.PageRequestDto;
import com.plazoleta.application.dto.request.SearchPlateRequestDto;
import com.plazoleta.application.dto.response.AuthRespondeDto;
import com.plazoleta.application.handler.ILoginHandler;
import com.plazoleta.application.handler.IOrderHandler;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.OrderListModel;
import com.plazoleta.domain.model.Orders;
import com.plazoleta.domain.model.Plate;
import com.plazoleta.infrastructure.out.jpa.entity.OrderEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/Orders")
@RequiredArgsConstructor
public class OrdersController {
	
	private final IOrderHandler orderHandler;
	
	@Operation(summary = "Order", description = "Add a new Order")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Exitoso"),
		@ApiResponse(responseCode = "204", description = "No hay información"),
		@ApiResponse(responseCode = "500", description = "Error interno"),
		@ApiResponse(responseCode = "400", description = "Error de request"),
		@ApiResponse(responseCode = "401", description = "No autorizado")})
	@PostMapping(value="/order", produces = "application/json")
	public ResponseEntity<MessageResponse> login(@RequestBody OrderRequestDto request ) {		
		
		return ResponseEntity.ok(orderHandler.saveOrder(request));
	}
	
	@GetMapping("/listOrders")
	public ResponseEntity<List<OrderListModel>> listOrders(
	        @RequestParam String status,
	        @RequestParam Long idEmployee,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size) {
	    
	    OrderStatusRequestDto orderDto = new OrderStatusRequestDto();
	    orderDto.setIdEmployee(idEmployee);
	    orderDto.setStatus(status);
	    
	    PageRequestDto pageRequestDto = new PageRequestDto();
	    pageRequestDto.setPage(page);
	    pageRequestDto.setSize(size);	
	    orderDto.setPageRequestDto(pageRequestDto);
	    
	    // 1. Obtener la lista del handler (debe devolver DTOs, no Entities)
	    List<OrderListModel> orders = orderHandler.orders(orderDto);
	    
	    // 2. Retornar correctamente envuelto en ResponseEntity
	    return ResponseEntity.ok(orders);
	}

}

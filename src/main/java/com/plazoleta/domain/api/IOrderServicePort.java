package com.plazoleta.domain.api;

import java.util.List;

import com.plazoleta.application.dto.request.AssignOrderRequestDto;
import com.plazoleta.application.dto.request.OrderRequestDto;
import com.plazoleta.application.dto.request.OrderStatusRequestDto;
import com.plazoleta.application.dto.request.SmsRequestDto;
import com.plazoleta.domain.model.AssignOrderRequest;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.OrderListModel;
import com.plazoleta.domain.model.OrderRequest;
import com.plazoleta.domain.model.OrderStatusRequest;
import com.plazoleta.domain.model.Orders;
import com.plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import com.plazoleta.infrastructure.out.jpa.entity.OrderPlateEntity;


public interface IOrderServicePort {
	
	MessageResponse saveOrder(OrderRequest orderRequest);
	List<OrderListModel> orders(OrderStatusRequest orderStatusRequest);
	List<OrderListModel> ordersAsignStatus(AssignOrderRequest assignOrderRequest);
	void sendSmdNotify(Long idOrder);
	MessageResponse updateStatusOrder(String secutiryCode, Long orderId);
	MessageResponse cancelStatusOrder(Long orderId);
	

}


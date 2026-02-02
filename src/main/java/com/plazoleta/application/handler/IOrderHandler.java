package com.plazoleta.application.handler;

import com.plazoleta.application.dto.request.AssignOrderRequestDto;
import com.plazoleta.application.dto.request.OrderRequestDto;
import com.plazoleta.application.dto.request.OrderStatusRequestDto;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.OrderListModel;

import java.util.List;

public interface IOrderHandler {
	MessageResponse saveOrder(OrderRequestDto orderRequestDto);
	
	List<OrderListModel> orders(OrderStatusRequestDto orderStatusRequestDto);
	
	List<OrderListModel> ordersAsignStatus(AssignOrderRequestDto assignOrderRequestDto);
	
	void sendSmsNotify(Long idOrder);
	
	MessageResponse updateStatusOrder(String secutiryCode,Long idOrder);
	
	MessageResponse cancelStatusOrder(Long idOrder);


}

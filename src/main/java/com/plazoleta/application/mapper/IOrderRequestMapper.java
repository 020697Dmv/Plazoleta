package com.plazoleta.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.plazoleta.application.dto.request.AssignOrderRequestDto;
import com.plazoleta.application.dto.request.OrderRequestDto;
import com.plazoleta.application.dto.request.OrderStatusRequestDto;
import com.plazoleta.application.dto.request.PageRequestDto;
import com.plazoleta.domain.model.AssignOrderRequest;
import com.plazoleta.domain.model.OrderRequest;
import com.plazoleta.domain.model.OrderStatusRequest;
import com.plazoleta.domain.model.PageRequest;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderRequestMapper {
	
	
	OrderRequest toOrderRequest(OrderRequestDto orderRequestDto);
	
	@Mapping(source = "pageRequestDto", target = "pageRequest")
    OrderStatusRequest toOrderStatusRequest(OrderStatusRequestDto orderStatusRequestDto);

    PageRequest toPageRequest(PageRequestDto pageRequestDto);
    
	@Mapping(source = "pageRequestDto", target = "pageRequest")
    AssignOrderRequest toAssignOrderRequest(AssignOrderRequestDto assignOrderRequestDto);
}

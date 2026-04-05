package com.plazoleta.infrastructure.out.jpa.mapper;

import com.plazoleta.domain.model.OrderListModel;
import com.plazoleta.domain.model.OrderPlateDetails;
import com.plazoleta.domain.model.OrderPlateRequest;
import com.plazoleta.domain.model.Orders;
import com.plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import com.plazoleta.infrastructure.out.jpa.entity.OrderPlateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderEntityMapper {
	
	@Mapping(source = "restaurant", target = "nit")
	@Mapping(source = "fkEmployeeId", target = "idEmpleado") 
	Orders toOrder(OrderEntity orderEntity);

	@Mapping(target = "restaurant", ignore = true)
	@Mapping(source = "idEmpleado", target = "fkEmployeeId") 
	OrderEntity toEntity(Orders order);
    

        @Mapping(source = "id", target = "id")
        @Mapping(source = "clientId", target = "clientId")
        @Mapping(source = "date", target = "date")
        @Mapping(source = "status", target = "status")
        @Mapping(source = "restaurant", target = "restaurantName")
        @Mapping(source = "orderPlates", target = "details")
        @Mapping(source = "fkEmployeeId", target = "idEmpleado")
    OrderListModel toListModel(OrderEntity orderEntity);

    // Mapeo para los items de la lista de platos

        @Mapping(source = "plate.namePlate", target = "plateName")
        @Mapping(source = "quantity", target = "quantity")
    OrderPlateDetails toDetails(OrderPlateEntity orderPlateEntity);

    @Mapping(source = "plate.id", target = "plateId")
    OrderPlateRequest toOrderPlateRequest(OrderPlateEntity orderPlateEntity);

    @Mapping(source = "plateId", target = "plate.id")
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "id", ignore = true)
    OrderPlateEntity toOrderPlateEntity(OrderPlateRequest orderPlateRequest);

    List<OrderListModel> toOrderList(List<OrderEntity> orderEntityList);
    
}

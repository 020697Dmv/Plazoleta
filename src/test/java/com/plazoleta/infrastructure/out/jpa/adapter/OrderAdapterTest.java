package com.plazoleta.infrastructure.out.jpa.adapter;

import com.plazoleta.domain.model.AssignOrderRequest;
import com.plazoleta.domain.model.OrderListModel;
import com.plazoleta.infrastructure.exception.OrderNotFoundException;
import com.plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import com.plazoleta.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.plazoleta.infrastructure.out.jpa.repository.IOrderPlateRepository;
import com.plazoleta.infrastructure.out.jpa.repository.IOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderAdapterTest {

    @Mock
    private IOrderPlateRepository orderPlateRepository;

    @Mock
    private IOrderRepository orderRepository;

    @Mock
    private IOrderEntityMapper orderEntityMapper;

    @InjectMocks
    private OrderAdapter orderAdapter;

    @Test
    void saveOrder_delegatesToRepository() {
        OrderEntity in = new OrderEntity();
        OrderEntity saved = new OrderEntity();
        when(orderRepository.save(in)).thenReturn(saved);
        assertSame(saved, orderAdapter.saveOrder(in));
        verify(orderRepository).save(in);
    }

    @Test
    void toResponseList_empty_throws() {
        Page<OrderEntity> empty = Page.empty();
        when(orderRepository.findAllByRestaurantNitAndStatus(eq(1L), eq("S"), (org.springframework.data.domain.Pageable) any())).thenReturn(empty);
        assertThrows(OrderNotFoundException.class, () -> orderAdapter.toResponseList(new com.plazoleta.domain.model.OrderStatusRequest("S", null, new com.plazoleta.domain.model.PageRequest(0,10)), 1L));
    }

    @Test
    void asignnedStatusAsign_savesAndReturns() {
        AssignOrderRequest ar = new AssignOrderRequest();
        ar.setPageRequest(new com.plazoleta.domain.model.PageRequest(0,10));
        OrderEntity orderEntity = new OrderEntity();

        OrderEntity e = new OrderEntity();
        List<OrderEntity> content = List.of(e);
        Page<OrderEntity> page = new PageImpl<>(content);

        when(orderRepository.findAllByRestaurantNit(eq(2L), (org.springframework.data.domain.Pageable) any())).thenReturn(page);
        when(orderEntityMapper.toOrderList(content)).thenReturn(List.of(new OrderListModel()));

        List<OrderListModel> result = orderAdapter.asignnedStatusAsign(ar, 2L, orderEntity);
        assertEquals(1, result.size());
        verify(orderRepository).save(orderEntity);
    }

    @Test
    void findById_delegates() {
        when(orderRepository.findByIdAndRestaurantNit(1L, 2L)).thenReturn(Optional.of(new OrderEntity()));
        Optional<OrderEntity> r = orderAdapter.findById(1L,2L);
        assertTrue(r.isPresent());
    }

}

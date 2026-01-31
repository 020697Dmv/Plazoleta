package com.plazoleta.domain.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.OrderPlateRequest;
import com.plazoleta.domain.model.OrderRequest;
import com.plazoleta.domain.spi.IOrderPersistencePort;
import com.plazoleta.domain.spi.IPlatePersistencePort;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.infrastructure.exception.UserNotFoundException;
import com.plazoleta.infrastructure.out.jpa.entity.OrderEntity;
import com.plazoleta.infrastructure.out.jpa.entity.PlateEntity;
import com.plazoleta.infrastructure.out.jpa.entity.RestaurantEntity;
import com.plazoleta.infrastructure.out.jpa.entity.UserEntity;

@ExtendWith(MockitoExtension.class)
public class OrderUseCaseTest {

    @Mock
    private IOrderPersistencePort orderPersistencePort;

    @Mock
    private IPlatePersistencePort platePersistencePort;

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @InjectMocks
    private OrderUseCase orderUseCase;

    private Authentication authentication;
    private SecurityContext securityContext;

    @BeforeEach
    void setUp() {
        authentication = mock(Authentication.class);
        securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
    }

    private void mockSecurityContext(boolean isClient) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userEntity);
        when(authentication.getAuthorities()).thenAnswer(invocation ->
            isClient ? Collections.singletonList(new SimpleGrantedAuthority("CLIENT")) : Collections.emptyList()
        );
    }

    @Test
    void saveOrder_whenUserIsClientAndHasNoActiveOrders_shouldSaveOrder() {
        mockSecurityContext(true);
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setRestaurantId(1L);
        OrderPlateRequest plateRequest = new OrderPlateRequest();
        plateRequest.setPlateId(1L);
        plateRequest.setQuantity(1);
        orderRequest.setPlates(Collections.singletonList(plateRequest));

        when(orderPersistencePort.existsByClientIdAndStatusIn(anyLong(), any(List.class))).thenReturn(false);

        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setNit(1L);
        when(restaurantPersistencePort.findByIdEntity(1L)).thenReturn(Optional.of(restaurantEntity));

        PlateEntity plateEntity = new PlateEntity();
        plateEntity.setRestaurant(restaurantEntity);
        when(platePersistencePort.findById(1L)).thenReturn(Optional.of(plateEntity));

        MessageResponse response = orderUseCase.saveOrder(orderRequest);

        assertEquals("Order created with id Client 1", response.getMessage());
        verify(orderPersistencePort).saveOrder(any(OrderEntity.class));
    }

    @Test
    void saveOrder_whenUserIsNotClient_shouldThrowException() {
        mockSecurityContext(false);
        OrderRequest orderRequest = new OrderRequest();
        assertThrows(UserNotFoundException.class, () -> orderUseCase.saveOrder(orderRequest));
        verify(orderPersistencePort, never()).saveOrder(any(OrderEntity.class));
    }

    @Test
    void saveOrder_whenUserHasActiveOrders_shouldReturnMessage() {
        mockSecurityContext(true);
        OrderRequest orderRequest = new OrderRequest();
        when(orderPersistencePort.existsByClientIdAndStatusIn(anyLong(), any(List.class))).thenReturn(true);

        MessageResponse response = orderUseCase.saveOrder(orderRequest);

        assertEquals("Ya tienes un pedido en curso.", response.getMessage());
        verify(orderPersistencePort, never()).saveOrder(any(OrderEntity.class));
    }
}

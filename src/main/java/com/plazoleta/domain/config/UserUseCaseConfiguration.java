 package com.plazoleta.domain.config;

import com.plazoleta.domain.api.IUserServicePort;
import com.plazoleta.domain.spi.IRestaurantEmployeePersistencePort;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.domain.spi.IUserPersistencePort;
import com.plazoleta.domain.usecase.UserUseCase;
import com.plazoleta.infrastructure.out.jpa.adapter.UserAdapter;
import com.plazoleta.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.plazoleta.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.plazoleta.infrastructure.out.jpa.repository.IUserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class UserUseCaseConfiguration {
    public IUserServicePort usuarioServicioPort(IUserPersistencePort userPersistencePort,IRestaurantEmployeePersistencePort restaurantEmployeePersistencePort,
    		IRestaurantPersistencePort restaurantPersistencePort) {
        return new UserUseCase(userPersistencePort, restaurantEmployeePersistencePort, restaurantPersistencePort);
    }

    public IUserPersistencePort usuarioPersistencePort(IUserRepository usuarioRepository, IUserEntityMapper usuarioEntidadMapeo) {
        return new UserAdapter(usuarioRepository, usuarioEntidadMapeo);
    }
}

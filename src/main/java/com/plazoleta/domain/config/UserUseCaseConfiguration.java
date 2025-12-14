package com.plazoleta.domain.config;

import com.plazoleta.domain.api.IUserServicePort;
import com.plazoleta.domain.spi.IUserPersistencePort;
import com.plazoleta.domain.usecase.UserUseCase;
import com.plazoleta.infrastructure.out.jpa.adapter.UsuarioAdapter;
import com.plazoleta.infrastructure.out.jpa.mapper.IUsuarioEntidadMapeo;
import com.plazoleta.infrastructure.out.jpa.repository.IUsuarioRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class UserUseCaseConfiguration {
    public IUserServicePort usuarioServicioPort(IUserPersistencePort userPersistencePort) {
        return new UserUseCase(userPersistencePort);
    }

    public IUserPersistencePort usuarioPersistencePort(IUsuarioRepository usuarioRepository, IUsuarioEntidadMapeo usuarioEntidadMapeo) {
        return new UsuarioAdapter(usuarioRepository, usuarioEntidadMapeo);
    }
}

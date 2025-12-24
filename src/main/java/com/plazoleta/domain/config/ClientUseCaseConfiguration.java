package com.plazoleta.domain.config;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.plazoleta.domain.api.IClientServicePort;
import com.plazoleta.domain.spi.IClientPersistencePort;
import com.plazoleta.domain.usecase.ClientUseCase;
import com.plazoleta.infrastructure.out.jpa.adapter.ClientAdapter;
import com.plazoleta.infrastructure.out.jpa.adapter.UserAdapter;
import com.plazoleta.infrastructure.out.jpa.mapper.IClientEntityMapper;
import com.plazoleta.infrastructure.out.jpa.repository.IClientRepository;

public class ClientUseCaseConfiguration {
	

	public IClientServicePort clientServicePort(IClientPersistencePort  clientPersistencePort) {
			return new ClientUseCase(clientPersistencePort); 
	}
	
	
	public IClientPersistencePort clientPersistencePort(IClientRepository clientRepository,IClientEntityMapper 
			clientEntityMapper) {
		return new ClientAdapter(clientRepository,clientEntityMapper);
	}

}

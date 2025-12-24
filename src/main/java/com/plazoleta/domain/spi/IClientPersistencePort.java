package com.plazoleta.domain.spi;

import java.util.Optional;

import com.plazoleta.domain.model.Client;

public interface IClientPersistencePort {
	
	Client saveClient(Client client);
	
	Optional<Client> findById(Long id);
}

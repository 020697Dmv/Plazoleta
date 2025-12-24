package com.plazoleta.domain.usecase;

import org.springframework.stereotype.Service;

import com.plazoleta.domain.api.IClientServicePort;
import com.plazoleta.domain.model.Client;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.spi.IClientPersistencePort;
import com.plazoleta.domain.spi.IEmployeePersistenciePort;
import com.plazoleta.infrastructure.exception.ClientAlreadyExistException;
import com.plazoleta.infrastructure.exception.EmployeeAlreadyExistException;
import com.plazoleta.infrastructure.out.jpa.util.Role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ClientUseCase  implements IClientServicePort{
	
	
	private final IClientPersistencePort  clientPersistencePort;
	
	@Override
	public MessageResponse saveClient(Client client) {

		client.setRole(Role.CLIENT);

		if(clientPersistencePort.findById(client.getId()).isPresent()) {
			throw new ClientAlreadyExistException();
		}
		Client saveClient=clientPersistencePort.saveClient(client);
		return new MessageResponse(String.format("Client created with id %d", saveClient.getId()));
	}

}

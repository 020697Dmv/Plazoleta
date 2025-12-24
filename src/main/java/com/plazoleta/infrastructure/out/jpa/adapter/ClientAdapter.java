package com.plazoleta.infrastructure.out.jpa.adapter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.plazoleta.domain.model.Client;
import com.plazoleta.domain.spi.IClientPersistencePort;
import com.plazoleta.infrastructure.out.jpa.entity.ClientEntity;
import com.plazoleta.infrastructure.out.jpa.mapper.IClientEntityMapper;
import com.plazoleta.infrastructure.out.jpa.mapper.IEmployeeEntityMapper;
import com.plazoleta.infrastructure.out.jpa.repository.IClientRepository;
import com.plazoleta.infrastructure.out.jpa.repository.IEmployeeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ClientAdapter implements IClientPersistencePort{
	
	private final IClientRepository clientRepository;
	
	private final IClientEntityMapper clientEntityMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Client saveClient(Client client) {
		client.setPassword(passwordEncoder.encode(client.getPassword()));
		ClientEntity clientEntity=clientRepository.save(clientEntityMapper.toEntity(client));
		return clientEntityMapper.toClient(clientEntity);
	}

	@Override
	public Optional<Client> findById(Long id) {
		return clientRepository.findById(id).map(clientEntityMapper::toClient);

	}

}

package com.plazoleta.application.handler.impl;

import org.springframework.stereotype.Service;

import com.plazoleta.application.dto.request.ClientResquestDto;
import com.plazoleta.application.handler.IClientHandler;
import com.plazoleta.application.mapper.IClientResquestMapper;
import com.plazoleta.application.mapper.IEmployeeRequestMapper;
import com.plazoleta.domain.api.IClientServicePort;
import com.plazoleta.domain.api.IEmployeeServicePort;
import com.plazoleta.domain.model.Client;
import com.plazoleta.domain.model.MessageResponse;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientHandler implements IClientHandler{
	
	public final IClientServicePort  clientServicePort;
	public final IClientResquestMapper clientResquestMapper;
	
	
	@Override
	public MessageResponse saveClient(ClientResquestDto clientResquestDto) {
		Client client=clientResquestMapper.toClient(clientResquestDto);
		MessageResponse messageResponse=clientServicePort.saveClient(client);
		return messageResponse;
	}

}

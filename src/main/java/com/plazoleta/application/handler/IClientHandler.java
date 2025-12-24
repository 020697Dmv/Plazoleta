package com.plazoleta.application.handler;

import com.plazoleta.application.dto.request.ClientResquestDto;
import com.plazoleta.application.dto.request.EmployeeRequestDto;
import com.plazoleta.domain.model.MessageResponse;

public interface IClientHandler {
	
	MessageResponse saveClient(ClientResquestDto clientResquestDto);

}

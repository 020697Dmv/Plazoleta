package com.plazoleta.domain.api;

import com.plazoleta.domain.model.Client;
import com.plazoleta.domain.model.MessageResponse;

public interface IClientServicePort {
	
	
	MessageResponse saveClient(Client client);

}

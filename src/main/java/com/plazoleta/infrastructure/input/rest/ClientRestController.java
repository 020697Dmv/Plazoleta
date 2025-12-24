package com.plazoleta.infrastructure.input.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plazoleta.application.dto.request.ClientResquestDto;
import com.plazoleta.application.dto.request.EmployeeRequestDto;
import com.plazoleta.application.handler.IClientHandler;
import com.plazoleta.application.handler.IEmployeeHandler;
import com.plazoleta.domain.model.MessageResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/Client")
@RequiredArgsConstructor
public class ClientRestController {
	
	private final IClientHandler  clientHandler;


	@Operation(summary = "Add a new Client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Client already exists", content = @Content)
    })
    @PostMapping("/saveClient")
    public ResponseEntity<MessageResponse> saveClient(@RequestBody ClientResquestDto clientResquestDto) {
        return new ResponseEntity<>(clientHandler.saveClient(clientResquestDto), HttpStatus.CREATED);
    }
}

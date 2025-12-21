package com.plazoleta.infrastructure.input.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.plazoleta.application.dto.request.UserRequesteDto;
import com.plazoleta.application.dto.response.StringResponseDto;
import com.plazoleta.application.handler.IUserHandler;
import com.plazoleta.domain.model.MessageResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/User")
@RequiredArgsConstructor
public class UserRestController {
	
	private final IUserHandler usuarioHandler;
	
	@Operation(summary = "Add a new User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content)
    })
    @PostMapping("/saveUser")
    public ResponseEntity<MessageResponse> saveUser(@RequestBody UserRequesteDto usuarioRequesteDto) {
        return new ResponseEntity<>(usuarioHandler.saveUser(usuarioRequesteDto), HttpStatus.CREATED);
    }
	
}

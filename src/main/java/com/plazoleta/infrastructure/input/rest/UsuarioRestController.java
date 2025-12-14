package com.plazoleta.infrastructure.input.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.plazoleta.application.dto.request.UsuarioRequesteDto;
import com.plazoleta.application.dto.response.StringRespuestaDto;
import com.plazoleta.application.handler.IUsuarioHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/neighborhood")
@RequiredArgsConstructor
public class UsuarioRestController {
	
	private final IUsuarioHandler usuarioHandler;
	
	@Operation(summary = "Add a new neighborhood")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Neighborhood created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Neighborhood already exists", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<StringRespuestaDto> saveUsuario(@RequestBody UsuarioRequesteDto usuarioRequesteDto) {
        return new ResponseEntity<>(usuarioHandler.guardarUsuario(usuarioRequesteDto), HttpStatus.CREATED);
    }
	
}

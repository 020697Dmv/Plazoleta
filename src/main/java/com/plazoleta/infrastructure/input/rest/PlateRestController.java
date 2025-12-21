package com.plazoleta.infrastructure.input.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plazoleta.application.dto.request.PlateRequestDto;
import com.plazoleta.application.dto.request.UserRequesteDto;
import com.plazoleta.application.handler.IPlateHandler;
import com.plazoleta.application.handler.IUserHandler;
import com.plazoleta.domain.model.MessageResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/Plate")
@RequiredArgsConstructor
public class PlateRestController {
	
	private final IPlateHandler plateHandler;
	
	@Operation(summary = "Add a new Plate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Plate created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Plate already exists", content = @Content)
    })
    @PostMapping("/savePlate")
    public ResponseEntity<MessageResponse> savePlate(@RequestBody PlateRequestDto plateRequestDto) {
        return new ResponseEntity<>(plateHandler.savePlate(plateRequestDto), HttpStatus.CREATED);
    }

}

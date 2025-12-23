package com.plazoleta.infrastructure.input.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plazoleta.application.dto.request.EmployeeRequestDto;
import com.plazoleta.application.dto.request.UserRequesteDto;
import com.plazoleta.application.handler.IEmployeeHandler;
import com.plazoleta.domain.model.MessageResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/Employee")
@RequiredArgsConstructor
public class EmployeeRestController {
	
	private final IEmployeeHandler employeeHandler;
	
	@Operation(summary = "Add a new Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content)
    })
    @PostMapping("/saveEmployee")
    public ResponseEntity<MessageResponse> saveEmployee(@RequestBody EmployeeRequestDto employeeRequestDto) {
        return new ResponseEntity<>(employeeHandler.saveEmployee(employeeRequestDto), HttpStatus.CREATED);
    }


}

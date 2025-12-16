package com.plazoleta.infrastructure.input.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plazoleta.application.dto.request.RestaurantRequestDto;
import com.plazoleta.application.dto.request.UserRequesteDto;
import com.plazoleta.application.dto.response.StringResponseDto;
import com.plazoleta.application.handler.IRestaurantHandler;
import com.plazoleta.application.handler.IUserHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/Restaurant")
@RequiredArgsConstructor
public class RestaurantRestController {
	
	private final IRestaurantHandler  restaurantHandler;
	
	@Operation(summary = "Add a new Restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Restaurant already exists", content = @Content)
    })
    @PostMapping("/saveRestaurant")
    public ResponseEntity<StringResponseDto> saveRestaurant(@RequestBody RestaurantRequestDto restaurantRequestDto) {
        return new ResponseEntity<>(restaurantHandler.saveRestaurant(restaurantRequestDto), HttpStatus.CREATED);
    }

}

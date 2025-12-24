package com.plazoleta.infrastructure.input.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.plazoleta.application.dto.response.RestaurantPageResponseDto;
import com.plazoleta.application.dto.request.PageRequestDto;
import com.plazoleta.application.dto.request.RestaurantRequestDto;
import com.plazoleta.application.dto.request.UserRequesteDto;
import com.plazoleta.application.dto.response.StringResponseDto;
import com.plazoleta.application.handler.IRestaurantHandler;
import com.plazoleta.application.handler.IUserHandler;
import com.plazoleta.application.mapper.IRestaurantResponseMapper;
import com.plazoleta.domain.model.MessageResponse;
import com.plazoleta.domain.model.Restaurant;

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
	
	private final IRestaurantResponseMapper restaurantResponseMapper;
	
	@Operation(summary = "Add a new Restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Restaurant already exists", content = @Content)
    })
    @PostMapping("/saveRestaurant")
    public ResponseEntity<MessageResponse> saveRestaurant(@RequestBody RestaurantRequestDto restaurantRequestDto) {
        return new ResponseEntity<>(restaurantHandler.saveRestaurant(restaurantRequestDto), HttpStatus.CREATED);
    }
	
	@GetMapping("/listRestaurants")
	public ResponseEntity<List<RestaurantPageResponseDto>> listRestaurants(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size) {
	    
	    PageRequestDto pageRequestDto = new PageRequestDto();
	    pageRequestDto.setPage(page);
	    pageRequestDto.setSize(size);
	    
	    return ResponseEntity.ok(restaurantHandler.getAllRestaurants(pageRequestDto));
	}

}

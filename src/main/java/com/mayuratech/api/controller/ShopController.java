package com.mayuratech.api.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayuratech.api.entity.ShopDetails;
import com.mayuratech.api.payload.ShopDetailsDto;
import com.mayuratech.api.service.ShopService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api")
public class ShopController {
	Logger demologger = LogManager.getLogger(ShopController.class.getName());
	@Autowired
	private ShopService shopService;
	
	@PostMapping("/add")
	@Operation(
			summary="Add Shop Details" ,description = "Add the shop Details",
			responses = { @ApiResponse(responseCode = "200",
			description = "shop Details added successfully!",
			content = @Content(mediaType = "Application/Json",
			examples = { @ExampleObject(value = "{\"code\":200,\"status\":\"ok\",\"Message\":\"shop Details added successfully!\"}")})),
					@ApiResponse(responseCode = "500",
					description = "Internal Server Error!",
					content = @Content(mediaType = "Application/Json",
					examples = {@ExampleObject(value = "{\"code\":500,\"status\":\"internal server error\",\"Message\":\"Something went wrong!\"}")}))
			})
	public ResponseEntity<Object> addShopDetails(@RequestBody @Valid ShopDetailsDto shopDto){
		demologger.info("Unexpected error from ShopDetailsServiceImpl");
		ShopDetails details = shopService.saveShopDetails(shopDto);
		return new ResponseEntity<>(details,HttpStatus.ACCEPTED);
	}
}

package com.mayuratech.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class IAMController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	

}

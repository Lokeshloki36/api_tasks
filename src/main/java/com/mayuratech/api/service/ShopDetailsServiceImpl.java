package com.mayuratech.api.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mayuratech.api.entity.ShopDetails;
import com.mayuratech.api.payload.ShopDetailsDto;
import com.mayuratech.api.repository.ShopRepository;

@Service
public class ShopDetailsServiceImpl implements ShopService {
	Logger demologger = LogManager.getLogger(ShopDetailsServiceImpl.class.getName());
	
	@Autowired
	private ShopRepository shopRepository;

	@Override
	public ShopDetails saveShopDetails(ShopDetailsDto shopDto) {
		try {
		ShopDetails details = mapToentity(shopDto);
		demologger.info("Accessing Database");
		shopRepository.save(details);
		return details;
		}catch(Exception e) {
			demologger.error("Unexpected error from ShopDetailsServiceImpl");
		}
		return null;	
	}
	public static ShopDetails mapToentity(ShopDetailsDto shopDto) {
		ShopDetails details = new ShopDetails();
		details.setShopName(shopDto.getShopName());
		details.setEmail(shopDto.getEmail());
		details.setMobile(shopDto.getMobile());
		details.setAddress(shopDto.getAddress());
		return details;
	}
	
		
	}


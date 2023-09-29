package com.mayuratech.api.service;

import com.mayuratech.api.entity.ShopDetails;
import com.mayuratech.api.payload.ShopDetailsDto;

public interface ShopService {
	
	public ShopDetails saveShopDetails(ShopDetailsDto shopDto);
}

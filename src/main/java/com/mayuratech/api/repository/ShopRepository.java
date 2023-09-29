package com.mayuratech.api.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mayuratech.api.entity.ShopDetails;

public interface ShopRepository extends JpaRepository<ShopDetails, Long>{
	
}

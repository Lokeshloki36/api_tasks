package com.mayuratech.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mayuratech.api.entity.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long>{
	

}

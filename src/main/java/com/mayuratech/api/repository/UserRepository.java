package com.mayuratech.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mayuratech.api.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	User findByUserName(String userName);
	User findByEmailOrUserName(String email,String userName);
	boolean existsByEmail(String email);
	boolean existsByUserName(String userName);
}

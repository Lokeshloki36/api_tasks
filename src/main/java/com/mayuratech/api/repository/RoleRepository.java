package com.mayuratech.api.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<com.mayuratech.api.entity.Role, Long> {
	Optional<com.mayuratech.api.entity.Role> findByName(String name);
}

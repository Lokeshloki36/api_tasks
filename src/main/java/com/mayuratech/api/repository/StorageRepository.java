package com.mayuratech.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mayuratech.api.entity.ImageData;

public interface StorageRepository extends JpaRepository<ImageData, Long> {
	Optional<ImageData> findByName(String name);
}

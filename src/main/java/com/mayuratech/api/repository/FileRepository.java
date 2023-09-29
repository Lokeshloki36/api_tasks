package com.mayuratech.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mayuratech.api.entity.FileData;

@Repository
public interface FileRepository extends JpaRepository<FileData, Long>{
	Optional<FileData> findByName(String name);
}

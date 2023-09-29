package com.mayuratech.api.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
	public String uploadFile(MultipartFile file) throws IOException;
	public byte[] downloadFile(String name);
	byte[] downloadFileById(long id);
	public String uploadFileToFileSystem(MultipartFile file) throws IllegalStateException, IOException;
	public byte[] downloadFileFromFileSystem(String name) throws IOException;
}

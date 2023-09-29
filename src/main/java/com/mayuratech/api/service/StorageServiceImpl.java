package com.mayuratech.api.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mayuratech.api.entity.FileData;
import com.mayuratech.api.entity.ImageData;
import com.mayuratech.api.repository.FileRepository;
import com.mayuratech.api.repository.StorageRepository;
import com.mayuratech.api.utils.ImageUtils;

@Service
public class StorageServiceImpl implements StorageService{
	Logger demologger = LogManager.getLogger(StorageServiceImpl.class.getName());
	
	@Autowired
	private StorageRepository repository;
	
	@Autowired
	private FileRepository fileRepository;
	
	private final String FOLDER_PATH="C:/Users/blokesh/MyFiles_SB/";
	@Override
	public String uploadFile(MultipartFile file) throws IOException {
//		System.out.println(file.getName());
//		System.out.println(file.getOriginalFilename());
		ImageData imageData = repository.save(
				ImageData.builder()
				.name(file.getOriginalFilename())
				.type(file.getContentType())
				.image(ImageUtils.compressImage(file.getBytes()))
				.build()
				);
		if(imageData!=null) {
			demologger.info("File saved successfully : "+file.getOriginalFilename());
			return "File saved successfully : "+file.getOriginalFilename();
		}
		demologger.warn("File should not be null : ");
		return null;
	}

	@Override
	public byte[] downloadFile(String name) {
		Optional<ImageData> imageData = repository.findByName(name);
		 byte[] image = ImageUtils.decompressImage(imageData.get().getImage());
		 
		 if(image!=null) {
		 demologger.info("File Downloaded successfully! ");
		 }
		return image;
	}
	
	@Override
	public byte[] downloadFileById(long id) {
		Optional<ImageData> imageData = repository.findById(id);
		 byte[] image = ImageUtils.decompressImage(imageData.get().getImage());
		 if(image!=null) {
		 demologger.info("File Downloaded successfully! ");
		 }
		return image;
	}
	@Override
	public String uploadFileToFileSystem(MultipartFile file) throws IllegalStateException, IOException {
		String filePath=FOLDER_PATH+file.getOriginalFilename();
		FileData file_data = fileRepository.save(
				 FileData.builder().name(file.getOriginalFilename()).type(file.getContentType()).filePath(filePath).build()
				);
		if(file_data!=null) {
			demologger.info("File Upload successfully : "+file.getOriginalFilename());
			file.transferTo(new File(filePath));
			return "File Uploaded successfully : "+file.getOriginalFilename();
		}
		demologger.warn("File cannot not be null : ");
		return null;
	}
	
	@Override
	public byte[] downloadFileFromFileSystem(String name) throws IOException {
		Optional<FileData> imageData = fileRepository.findByName(name);
		String filePath = imageData.get().getFilePath();
		 byte[] image = Files.readAllBytes(new File(filePath).toPath());
		 Path downloadPath = Path.of(FOLDER_PATH+"downloaded/"+imageData.get().getName());
		 Files.write(downloadPath, image, StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING);
		 if(image!=null) {
		 demologger.info("File Downloaded successfully! ");
		 }
		return image;
	}

}

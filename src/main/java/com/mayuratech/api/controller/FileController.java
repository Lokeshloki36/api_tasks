package com.mayuratech.api.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mayuratech.api.service.StorageService;

@RestController
@RequestMapping("/file")
public class FileController {
	Logger demologger = LogManager.getLogger(FileController.class.getName());
	
	@Autowired
	private StorageService storageService;
	
	@PostMapping("/upload")
	public ResponseEntity<Object> addImage(@RequestParam("file")MultipartFile file){
		try {
			String uploadFile = storageService.uploadFile(file);
			if(uploadFile!=null) {
				demologger.info("File Uploaded Successfully!");
			return new ResponseEntity<Object>(uploadFile,HttpStatus.CREATED);
			}
		} catch (IOException e) {
			demologger.error("Something went wrong while uploading file");
			e.printStackTrace();
		}
		return null;	
	}
	
	@GetMapping("/download/{fileName}")
	public ResponseEntity<?> getImage(@PathVariable String fileName){
		byte[] downloadedFile = storageService.downloadFile(fileName);
		if(downloadedFile!=null) {
			demologger.info("Image Downloaded Successfully!");
			return  ResponseEntity.status(HttpStatus.OK)
					.contentType(MediaType.valueOf("image/png"))
					.body(downloadedFile);
		}
		return null;
		
	}
	
	@GetMapping("/downloadById/{id}")
	public ResponseEntity<?> getImage(@PathVariable long id){
		byte[] downloadedFile = storageService.downloadFileById(id);
		if(downloadedFile!=null) {
			demologger.info("Image Downloaded Successfully!");
			return  ResponseEntity.status(HttpStatus.OK)
					.contentType(MediaType.valueOf("image/png"))
					.body(downloadedFile);
		}
		return null;
		
	}
	
	@PostMapping("/fileSystem")
	public ResponseEntity<Object> uploadImage(@RequestParam("image")MultipartFile file){
		try {
			String uploadFile = storageService.uploadFileToFileSystem(file);
			if(uploadFile!=null) {
				demologger.info("Image Uploaded Successfully!");
			return new ResponseEntity<Object>(uploadFile,HttpStatus.CREATED);
			}
		} catch (IOException e) {
			demologger.error("Something went wrong while uploading file");
			e.printStackTrace();
		}
		return null;	
	}
	
	@GetMapping("/fileSystem/{fileName}")
	public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException{
		byte[] downloadedFile = storageService.downloadFileFromFileSystem(fileName);
		if(downloadedFile!=null) {
			demologger.info("Image Downloaded Successfully!");
			return  ResponseEntity.status(HttpStatus.OK)
					.contentType(MediaType.valueOf("image/png"))
					.body(downloadedFile);
		}
		return null;
		
	}
	
}

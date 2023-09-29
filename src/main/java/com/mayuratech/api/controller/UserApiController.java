package com.mayuratech.api.controller;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.List;

import javax.print.attribute.standard.Media;
import javax.xml.transform.stream.StreamResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.impl.InvalidContentTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mayuratech.api.entity.UserDetails;
import com.mayuratech.api.service.UserService;
import com.mayuratech.api.utils.PDFGenerator;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api")
public class UserApiController {
	
	Logger demologger = LogManager.getLogger(UserApiController.class.getName());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PDFGenerator pdfGenerator;

	// Task-1
	@PostMapping("/updateUser/{id}")
	@Operation(summary="Update the User",
			description = "Update the user Details",
			responses = { @ApiResponse(responseCode = "200",
			description = "User Details updated successfully!",
			content = @Content(mediaType = "Application/Json",
			examples = { @ExampleObject(value = "{\"code\":200,\"status\":\"ok\",\"Message\":\"User Details updated successfully!\"}"),})),
					@ApiResponse(responseCode = "500",
					description = "Internal Server Error!",
					content = @Content(mediaType = "Application/Json",
					examples = {@ExampleObject(value = "{\"code\":500,\"status\":\"internal server error\",\"Message\":\"Internal Server Error!\"}")}))
			})
	public ResponseEntity<Object> updateUser(@RequestBody UserDetails user,@PathVariable("id")long id){
		demologger.info("Accessing the Service Layer");
		UserDetails updatedUser = userService.updateUser(user,id);
		return new ResponseEntity<>(updatedUser,HttpStatus.OK);
	}

	// Task-2
	@GetMapping(value="/viewUser/{id}"/*,produces = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML}*/)
	@Operation(summary="Fetch the User",
			description = "Fetching user Details",
			responses = { @ApiResponse(responseCode = "200",
			description = "User Details fetched successfully!",
			content = @Content(mediaType = "Application/Json",
			examples = { @ExampleObject(value = "{\"code\":200,\"status\":\"ok\",\"Message\":\"User Details fetched successfully!\"}"),})),
					@ApiResponse(responseCode = "500",
					description = "Internal Server Error!",
					content = @Content(mediaType = "Application/Json",
					examples = {@ExampleObject(value = "{\"code\":500,\"status\":\"internal server error\",\"Message\":\"Internal Server Error!\"}")}))
			})
	public ResponseEntity<Object> getUser(@PathVariable("id") long id) {
		demologger.info("Accessing the Service Layer");
		UserDetails user = userService.getUserById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping(path="/users",produces = {"application/xml","application/json","text/plain","text/html"})
	@Operation(summary = "Fetch all users",
	description = "fetching all the records of the users",
	responses = {
			@ApiResponse(responseCode = "200",
					description = "fetch successfull",
					content = @Content(mediaType = "Application/Json",
					examples = {@ExampleObject(value = "{\"code\":200,\"status\":\"ok\",\"Message\":\"user records fetched successfully!\"}"),})),
							@ApiResponse(responseCode = "500",
							description = "Internal server Error!",
							content = @Content(mediaType = "Application/Json",
							examples = {@ExampleObject(value = "{\"code\":500,\"status\":\"internal server error\",\"Message\":\"Internal Server Error!\"}")}))
	})
	public ResponseEntity<List<UserDetails>> getAllUsers(){
		List<UserDetails> allUsers = userService.getAllUsers();
		return new ResponseEntity<>(allUsers,HttpStatus.OK);
	}
	
	@GetMapping(path="/users/text",produces=
		{MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<?> getListOfUsersInXml(Object list){
		Object response = userService.getAllUsers();
		//String dataInText = response.toString();
		return  new ResponseEntity<Object>(response,HttpStatus.OK);
	}
	
	
	@GetMapping(path="/usersPdf",produces=MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> generatePdf() throws Exception{
		List<UserDetails> users = userService.getAllUsers();
		ByteArrayInputStream pdf = pdfGenerator.generatePdf(users);
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-disposition", "inline;filename=customers.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(pdf));
	}
	@GetMapping(path="/text",produces= {MediaType.TEXT_PLAIN_VALUE,MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_XML_VALUE})
	public String getText() {
		return "Hello!";
	}
}

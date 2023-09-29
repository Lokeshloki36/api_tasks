package com.mayuratech.api.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.impl.InvalidContentTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mayuratech.api.entity.UserDetails;
import com.mayuratech.api.exception.ResourceNotFoundException;
import com.mayuratech.api.repository.UserDetailsRepository;

@Service
public class UserServiceImpl implements UserService {
	Logger demologger = LogManager.getLogger(UserServiceImpl.class.getName());
	
	@Autowired
	private UserDetailsRepository userRepository;

	@Override
	public UserDetails updateUser(UserDetails userDetails,long id) {
		try {
//		userRepository.findById(id).orElseThrow(
//				()-> new ResourceNotFoundException("User", "id", id));
		UserDetails obj = new UserDetails();
		obj.setId(id);
		obj.setName(userDetails.getName());
		obj.setAccountNumber(userDetails.getAccountNumber());
		obj.setBalance(userDetails.getBalance());
		demologger.info("Accessing Database for Updation");
		userRepository.save(obj);
		return obj;
		}catch(Exception e) {
			demologger.error("Unexpected error from UserServiceImpl");
		}
		return null;
	}

	@Override
	public UserDetails getUserById(long id) {
		demologger.info("Accessing Database for fetching a record");
		UserDetails user = userRepository.findById(id).orElseThrow(
				()->  new ResourceNotFoundException("user", "id", id));
		return user;
	}

	@Override
	public List<UserDetails> getAllUsers() {
		return userRepository.findAll(); 
	}

//	@Override
//	public Object getUsersByFormat(String format) {
//		List<UserDetails> list = userRepository.findAll();
//		if(format.equalsIgnoreCase("xml")) {
//			return ResponseEntity.ok().contentType(MediaType.APPLICATION_XML).body(list).p
//		}else if(format.equalsIgnoreCase("json")) {
//			return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(list);
//		}else if(format.equalsIgnoreCase("text")) {
//			return list.toString();
//		}
//		return new InvalidContentTypeException("Invalid format with name :"+format);
//	}

}

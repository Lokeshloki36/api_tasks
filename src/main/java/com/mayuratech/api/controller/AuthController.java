package com.mayuratech.api.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayuratech.api.entity.Role;
import com.mayuratech.api.entity.User;
import com.mayuratech.api.payload.JWTAuthRequest;
import com.mayuratech.api.payload.JWTAuthResponse;
import com.mayuratech.api.payload.SignUpDto;
import com.mayuratech.api.repository.RoleRepository;
import com.mayuratech.api.repository.UserRepository;
import com.mayuratech.api.security.CustomUserDetailsService;
import com.mayuratech.api.utils.JWTUtility;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JWTUtility jwtUtility ;
    
    @Autowired
    private CustomUserDetailsService userService;

    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody JWTAuthRequest jwtRequest) throws Exception{
    	
    	try {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        		jwtRequest.getUsernameOrEmail(), jwtRequest.getPassword()));
    	}catch(BadCredentialsException e) {
    		throw new Exception("Invalid Credentials",e); 
    	}
    	final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsernameOrEmail());
    	final String generatedToken = jwtUtility.generateToken(userDetails);

        return new ResponseEntity<>(new JWTAuthResponse(generatedToken),HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){

        // add check for username exists in a DB
        if(userRepository.existsByUserName(signUpDto.getUserName())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in DB
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // create user object
        User user = new User();
        user.setFirstName(signUpDto.getFirstName());
        user.setLastName(signUpDto.getLastName());
        user.setUserName(signUpDto.getUserName());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setMobile(signUpDto.getMobile());

        Role roles = roleRepository.findByName("user").get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }
}
package com.chatapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.entity.AuthRequest;
import com.chatapp.entity.Groups;
import com.chatapp.serviceImpl.GroupsServiceImpl;
import com.chatapp.util.JwtUtil;

@RestController
public class AuthenticationController {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private GroupsServiceImpl groupsServiceImpl;
	
	@GetMapping("/home")
	public ResponseEntity<?> welcome() {
	    List<Groups> groups = groupsServiceImpl.getAllGroups();
	    
	    StringBuilder response = new StringBuilder("Welcome to Anonymous Chat App. Here are the available groups:\n");
	    for (Groups group : groups) {
	        response.append(group.toString()).append("\n");
	    }
	    
	    return ResponseEntity.ok(response.toString());
	}

	
	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())
					);	
			System.out.println("User Valid");
		} 
		catch (Exception e) {
			throw new Exception("Invalid Username and Password");
		}
		System.out.println("Generating Token...");
		return jwtUtil.generateToken(authRequest.getUsername());
	}
}

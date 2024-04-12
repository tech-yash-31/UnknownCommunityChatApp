package com.chatapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.Converter.UserEntityToDto;
import com.chatapp.dto.UserDTO;
import com.chatapp.entity.User;
import com.chatapp.serviceImpl.GroupsServiceImpl;
import com.chatapp.serviceImpl.UserServiceImpl;

@RestController
public class UserController {

	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	GroupsServiceImpl groupsServiceImpl;
	
	@Autowired
	UserEntityToDto entityToDto;
	
	
	@PostMapping("/register")
	public User insert(@RequestBody User user) {
		return userServiceImpl.addUser(user);
	
	}
	
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers(){
		List<User> userlist =  userServiceImpl.getAllUsers();
		return userlist;
	}
	
	@GetMapping("/user/{userId}")
	public Optional<User> getUserByuserId(@PathVariable("userId") int userId) {
		
		return userServiceImpl.getUserByuserId(userId);
	}
	
	@PutMapping("/user/{userId}")
	public User updateUserByuserId(@RequestBody User user,@PathVariable("userId") int  userId) {
	  
		return userServiceImpl.updateUserByuserId(userId, user);
	}
	
	
	@DeleteMapping("/user/{userId}")
	public String deleteUserByuserId(@PathVariable int userId) {
		userServiceImpl.deleteUserByuserId(userId);
		return "User deleted Successfully";
	}
	
	@GetMapping("/userinfo")
    public ResponseEntity<List<UserDTO>> getAllUserDTOs() {
        List<UserDTO> userDTOs = userServiceImpl.getUsers();
        return ResponseEntity.ok(userDTOs);
    }
	
	@GetMapping("/userinfo/{userId}")
	public ResponseEntity<List<UserDTO>> getUserById(@PathVariable int userId) {
	        List<UserDTO> userDtoList = userServiceImpl.getUserById(userId);

	        if (userDtoList.isEmpty()) {
	            return ResponseEntity.notFound().build();
	        }
	        
	        return ResponseEntity.ok(userDtoList); 
	    }

}


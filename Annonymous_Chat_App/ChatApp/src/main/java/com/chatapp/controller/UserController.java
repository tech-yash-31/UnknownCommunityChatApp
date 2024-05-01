package com.chatapp.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.Converter.UserConverter;
import com.chatapp.Converter.UserEntityToDto;
import com.chatapp.dto.UserDTO;
import com.chatapp.dto.UserRegistrationDTO;
import com.chatapp.dto.UserResponseDTO;
import com.chatapp.dto.UserUpdateDTO;
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
	public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRegistrationDTO registrationDTO) {
        User newUser = UserConverter.convertToEntity(registrationDTO);
        User savedUser = userServiceImpl.addUser(newUser); 
        UserResponseDTO responseDTO = UserConverter.convertToDTO(savedUser);
        return ResponseEntity.ok(responseDTO);
    }
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<User> userList = userServiceImpl.getAllUsers();
        List<UserResponseDTO> dtos = userList.stream()
            .map(UserConverter::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
	
	
	@PutMapping("/user/{userId}")
	public ResponseEntity<UserResponseDTO> updateUserByUserId(@RequestBody UserUpdateDTO updateDTO, @PathVariable("userId") int userId) {
        User userToUpdate = UserConverter.convertToUpdateEntity(updateDTO);
        User updatedUser = userServiceImpl.updateUserByuserId(userId, userToUpdate);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        UserResponseDTO responseDTO = UserConverter.convertToDTO(updatedUser);
        return ResponseEntity.ok(responseDTO);
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


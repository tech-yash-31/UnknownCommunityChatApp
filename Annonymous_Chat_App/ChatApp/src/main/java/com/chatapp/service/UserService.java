package com.chatapp.service;

import java.util.List;
import java.util.Optional;

import com.chatapp.entity.User;

public interface UserService {

	User addUser(User user);
	List<User> getAllUsers();
	User updateUserByuserId(int userId,User user);
	String deleteUserByuserId(int userId);
	Optional<User> getUserByuserId(int userId);
}

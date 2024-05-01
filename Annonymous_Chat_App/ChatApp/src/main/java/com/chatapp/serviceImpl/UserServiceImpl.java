package com.chatapp.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.chatapp.Converter.UserEntityToDto;
import com.chatapp.dto.UserDTO;
import com.chatapp.entity.Groups;
import com.chatapp.entity.User;
import com.chatapp.repository.GroupsRepository;
import com.chatapp.repository.UserRepository;
import com.chatapp.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	GroupsRepository groupsRepository;
	
	@Autowired
	UserEntityToDto entityToDto;
	
	public User addUser(User user) {
		return userRepository.save(user); 
		
	}
	
	@Override
	public List<User> getAllUsers() {
		List<User> userlist =  userRepository.findAll();
		return userlist;	
	}

	
	@Override
	public User updateUserByuserId(@PathVariable int userId,@RequestBody User user) {
		Optional<User> usr = userRepository.findById(userId);
		if (usr.isPresent()) {
			User existusr = usr.get();
			existusr.setUsername(user.getUsername());
			existusr.setEmail(user.getEmail());
			existusr.setPassword(user.getPassword());
			userRepository.save(existusr);
			return user;
		}
		else {
			return null;	
		}
		
	}
	

	@Override
	public String deleteUserByuserId(@PathVariable int userId) {
		userRepository.deleteById(userId);
		return "User Deleted Successflly";
	}
	

	@Override
	public Optional<User> getUserByuserId(@PathVariable("userId") int userId) {
		
		return userRepository.findById(userId);
	}
	
	public List<UserDTO> getUsers() {
		List<Object[]> getspecUsers = userRepository.getUsers();
		List<UserDTO> userDto = entityToDto.convertEntityToDto(getspecUsers);
		
		return userDto;
	}	
	
	public List<UserDTO> getUserById(int userId) {
	    Object userObject = userRepository.getUserById(userId);
	    List<Object[]> wrappedUserObject = new ArrayList<>();
	    
	    if (userObject != null) {
	        wrappedUserObject.add((Object[]) userObject); 
	    }

	    return entityToDto.convertEntityToDto(wrappedUserObject);
	}
	
}

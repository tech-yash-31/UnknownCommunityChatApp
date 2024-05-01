package com.chatapp.Converter;

import com.chatapp.dto.UserRegistrationDTO;
import com.chatapp.dto.UserResponseDTO;
import com.chatapp.dto.UserUpdateDTO;
import com.chatapp.entity.User;

public class UserConverter {

    public static User convertToEntity(UserRegistrationDTO registrationDTO) {
        User user = new User();
        user.setUsername(registrationDTO.getUsername());
        user.setEmail(registrationDTO.getEmail());
        user.setPassword(registrationDTO.getPassword()); 
        return user;
    }

    public static UserResponseDTO convertToDTO(User user) {
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setUsername(user.getUsername());
        responseDTO.setEmail(user.getEmail());
        return responseDTO;
    }
    
    public static User convertToUpdateEntity(UserUpdateDTO updateDTO) {
        User user = new User();
        user.setUsername(updateDTO.getUsername());
        user.setEmail(updateDTO.getEmail());
        user.setPassword(updateDTO.getPassword()); 
        return user;
    }
}

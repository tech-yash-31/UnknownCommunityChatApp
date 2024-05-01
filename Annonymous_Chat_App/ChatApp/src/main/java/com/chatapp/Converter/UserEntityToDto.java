package com.chatapp.Converter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.chatapp.dto.UserDTO;



@Component	
public class UserEntityToDto {
	
	    public static List<UserDTO> convertEntityToDto(List<Object[]> entityList) {
	        List<UserDTO> dtoList = new ArrayList<>();
	        for (Object[] entity : entityList) {
	            String username = (String) entity[0];
	            String email = (String) entity[1];
	            dtoList.add(new UserDTO(username, email));
	        }
	        return dtoList;
	    }
	    
	}

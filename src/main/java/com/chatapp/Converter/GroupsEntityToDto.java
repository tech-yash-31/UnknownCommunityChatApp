package com.chatapp.Converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.chatapp.dto.GroupsDTO;


@Component
public class GroupsEntityToDto {

	public static List<GroupsDTO> convertEntityToDto(List<Object[]> entityList) {
        List<GroupsDTO> dtoList = new ArrayList<>();
        for (Object[] entity : entityList) {
            String groupName = (String) entity[0]; 
            dtoList.add(new GroupsDTO(groupName));
        }
        return dtoList;
    }

}

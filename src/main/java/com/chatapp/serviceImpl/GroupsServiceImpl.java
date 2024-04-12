package com.chatapp.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.chatapp.Converter.GroupsEntityToDto;
import com.chatapp.Converter.UserEntityToDto;
import com.chatapp.dto.GroupsDTO;
import com.chatapp.dto.MembersDTO;
import com.chatapp.entity.Groups;
import com.chatapp.entity.User;
import com.chatapp.repository.GroupsRepository;
import com.chatapp.repository.UserRepository;
import com.chatapp.service.GroupsService;

@Service
public class GroupsServiceImpl implements GroupsService {

	@Autowired
	GroupsRepository groupsRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	GroupsEntityToDto groupsEntityToDto;
	
	
	@Override
	public Groups addGroups(@RequestBody Groups groups) {
		return groupsRepository.save(groups);
	}


	@Override
	public List<Groups> getAllGroups() {
			List<Groups> groupslist = groupsRepository.findAll();
			return groupslist;
	}
	
	
	@Override
	public Optional<Groups> getGroupsById(@PathVariable("groupId") int groupId) {
			
		return groupsRepository.findById(groupId);
	}


	@Override
	public boolean joinGroup(int userId, int groupId) {
		Optional<User> userOpt = userRepository.findById(userId);
		Optional<Groups> groupsOpt = groupsRepository.findById(groupId);
		
		if (userOpt.isPresent() && groupsOpt.isPresent()) {
            User user = userOpt.get();
            Groups groups = groupsOpt.get();
            user.getGroups().add(groups); 
            userRepository.save(user); 
            return true;
        }

        return false;
	}
	
	public List<GroupsDTO> getGroupsinfo() {
		
		List<Object[]> getGroups = groupsRepository.getGroupsinfo();
		List<GroupsDTO> groupsDto = groupsEntityToDto.convertEntityToDto(getGroups);
		
		return groupsDto;
	
	
	}
	
	public GroupsDTO getGroupDtoById(int groupId) {
	    Optional<Groups> groupOptional = this.getGroupsById(groupId);

	    if(groupOptional.isPresent()) {
	        Groups group = groupOptional.get();
	        List<MembersDTO> memberDtos = group.getMembers()
	        		.stream()
	                .map(user -> new MembersDTO(user.getUsername()))
	                .collect(Collectors.toList());

	        return new GroupsDTO(group.getGroupName(), memberDtos);
	    }
	    return null; 
	}

	

}

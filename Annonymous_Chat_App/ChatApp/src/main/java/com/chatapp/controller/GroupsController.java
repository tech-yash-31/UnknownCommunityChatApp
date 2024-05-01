package com.chatapp.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.dto.GroupCreationDTO;
import com.chatapp.dto.GroupsDTO;
import com.chatapp.entity.Groups;
import com.chatapp.serviceImpl.GroupsServiceImpl;

@RestController
public class GroupsController {

	@Autowired
	GroupsServiceImpl groupsServiceImpl;
	
	@PostMapping("/create/newgroup")
	public ResponseEntity<GroupsDTO> createGroup(@RequestBody GroupCreationDTO groupCreationDTO) {
	    // Manually creating the Group entity, expecting the DTO to provide an ID
	    Groups newGroup = new Groups();
	    newGroup.setGroupId(groupCreationDTO.getGroupId()); 
	    newGroup.setGroupName(groupCreationDTO.getGroupName());

	    Groups savedGroup = groupsServiceImpl.addGroups(newGroup);
	    GroupsDTO responseGroupDTO = new GroupsDTO(savedGroup.getGroupName());
	    return ResponseEntity.ok(responseGroupDTO);
	}
	
	@GetMapping("/show/groups")
	public ResponseEntity<List<GroupsDTO>> displayAllGroups() {
        List<Groups> groups = groupsServiceImpl.getAllGroups();
        List<GroupsDTO> groupsDTOs = groups.stream()
            .map(group -> new GroupsDTO(group.getGroupName()))
            .collect(Collectors.toList());
        return ResponseEntity.ok(groupsDTOs);
    }
	
	@GetMapping("/show/activeGrups")
	public ResponseEntity<List<GroupsDTO>> getAllGroupNames() {
        List<GroupsDTO> groupNames = groupsServiceImpl.getGroupsinfo();
        return ResponseEntity.ok(groupNames);
    }
	
	@GetMapping("/specGroupinfo/{groupId}")
	public ResponseEntity<GroupsDTO> getGroupDtoById(@PathVariable("groupId") int groupId) {
        GroupsDTO groupDto = groupsServiceImpl.getGroupDtoById(groupId);
        if(groupDto != null) {
            return ResponseEntity.ok(groupDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

	
	@PostMapping("/users/{userId}/groups/{groupId}")
    public ResponseEntity<?> joinGroup(@PathVariable int userId, @PathVariable int groupId) {
        boolean result = groupsServiceImpl.joinGroup(userId, groupId);

        if (result) {
            return ResponseEntity.ok().body("Joined the Group Successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to join the group");
        }
    }
	
}
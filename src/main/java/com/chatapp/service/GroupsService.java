package com.chatapp.service;

import java.util.List;
import java.util.Optional;

import com.chatapp.entity.Groups;

public interface GroupsService {
	
	Groups addGroups(Groups groups);
	public boolean joinGroup(int userId, int groupId);
	Optional<Groups> getGroupsById(int groupId);
	List<Groups> getAllGroups();

}

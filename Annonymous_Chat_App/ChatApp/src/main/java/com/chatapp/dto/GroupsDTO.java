package com.chatapp.dto;

import java.util.List;

public class GroupsDTO {
    private String groupName;
    private List<MembersDTO> membersDTO;

    public GroupsDTO() {}


	public GroupsDTO(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public GroupsDTO(String groupName, List<MembersDTO> membersDTO) {
        this.groupName = groupName;
        this.membersDTO = membersDTO;
    }

    public List<MembersDTO> getMembers() {
        return membersDTO;
    }

    public void setMembers(List<MembersDTO> membersDTO) {
        this.membersDTO = membersDTO;
    }
}



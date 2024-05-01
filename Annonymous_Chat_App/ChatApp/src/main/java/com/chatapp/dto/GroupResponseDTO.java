package com.chatapp.dto;

public class GroupResponseDTO {
    private String groupName;

    public GroupResponseDTO() {}

    public GroupResponseDTO( String groupName) {
      
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}

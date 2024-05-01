package com.chatapp.dto;

public class GroupCreationDTO {
	private Integer groupId;
    private String groupName;

    public GroupCreationDTO() {}

	public GroupCreationDTO(Integer groupId, String groupName) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}

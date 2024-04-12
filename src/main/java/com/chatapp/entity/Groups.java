package com.chatapp.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Groups_Table")
public class Groups implements Serializable {

	@Id
	private Integer groupId;
	private String groupName;
	
//	@JsonManagedReference
	@ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY)
	private Set<User> members = new HashSet<>();

	public Groups() {
		super();	
	}

	public Groups(int groupId, String groupName, Set<User> members) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.members = members;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Set<User> getMembers() {
		return members;
	}

	public void setMembers(Set<User> members) {
		this.members = members;
	}

	@Override
	public String toString() {
		return "Groups [groupId=" + groupId + 
				", groupName=" + groupName +"]";
//				", members=" + members + "]";
	}
	
}

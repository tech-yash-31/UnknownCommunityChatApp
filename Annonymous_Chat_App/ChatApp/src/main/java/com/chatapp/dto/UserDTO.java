package com.chatapp.dto;


public class UserDTO {

	private String username;
	private String email;
	
	
	public UserDTO() {

	}
	
	public UserDTO(String username, String email) {
		super();
		this.username = username;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}


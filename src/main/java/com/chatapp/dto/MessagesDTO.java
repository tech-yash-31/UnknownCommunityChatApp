package com.chatapp.dto;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class MessagesDTO {
	
	@Id
	private String message;
    private String senderName;
    private String groupName;

}
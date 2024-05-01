package com.chatapp.dto;

import java.time.LocalDateTime;

import jakarta.persistence.PrePersist;

public class MessageSendingDTO {
    private String messageContent;
    private Integer userId;
    private Integer groupId;
    private LocalDateTime timestamp;
	
	@PrePersist
    protected void onCreate() {
        timestamp = LocalDateTime.now();
    }


    // Getters and Setters
    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}

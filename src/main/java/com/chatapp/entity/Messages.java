package com.chatapp.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Builder;

@Builder
@Entity
@Table(name = "Messages_Table")
public class Messages implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer messageId;
	private String messageContent;
	
    private Integer userId;	

    private Integer groupId;
    

	private LocalDateTime timestamp;
	
	@PrePersist
    protected void onCreate() {
        timestamp = LocalDateTime.now();
    }
	
	
	public Messages() {
		super();
	}

	public Messages(Integer messageId, String messageContent, int userId, Integer groupId,
			LocalDateTime timestamp) {
		super();
		this.messageId = messageId;
		this.messageContent = messageContent;
		this.userId = userId;
		this.groupId = groupId;
		this.timestamp = timestamp;
	}

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
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

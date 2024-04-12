package com.chatapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.config.ChatMessageHandler;
import com.chatapp.dto.MessagesDTO;
import com.chatapp.entity.Messages;
import com.chatapp.serviceImpl.MessagesServiceImpl;

@RestController
public class MessagesController {

	@Autowired
	private MessagesServiceImpl messagesServiceImpl;

	@Autowired
	private ChatMessageHandler handleMessage;

	@PostMapping("/topic/send/{communityName}")
	public ResponseEntity<?> sendMessage(@PathVariable String groupName, @RequestBody Messages messages) {
		messagesServiceImpl.processAndSendMessage(messages.getMessageContent(), messages.getGroupId(),
				messages.getUserId(), messages.getTimestamp());
		
		handleMessage.broadcastMessage(groupName, messages.getMessageContent(), groupName);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody MessagesDTO messageDTO) {
        messagesServiceImpl.processAndSendMessages(messageDTO);
        return ResponseEntity.ok().build();
    }
}

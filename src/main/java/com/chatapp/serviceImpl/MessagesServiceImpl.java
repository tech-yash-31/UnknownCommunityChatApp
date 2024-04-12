package com.chatapp.serviceImpl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatapp.config.ChatMessageHandler;
import com.chatapp.dto.MessagesDTO;
import com.chatapp.entity.Messages;
import com.chatapp.entity.User;
import com.chatapp.repository.MessagesRepository;
import com.chatapp.repository.UserRepository;
import com.chatapp.service.MessagesService;

@Service
public class MessagesServiceImpl implements MessagesService {

	@Autowired
	MessagesRepository messagesRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private ChatMessageHandler handleMessage;
	
	@Override
	public void processAndSendMessage(String messageContent, Integer groupId, int userId, LocalDateTime timestamp)
	{
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent() && validateMessage(messageContent)) {
            Messages messages = new Messages();
            messages.setMessageContent(messageContent);
            messages.setGroupId(groupId);
            messages.setUserId(userId);
            messages.setTimestamp(timestamp);
            messagesRepository.save(messages);
            
        } else {
            userRepository.deleteById(userId);
        }
	}
	
	public void processAndSendMessages(MessagesDTO messageDTO) {
	    if (validateMessages(messageDTO.getMessage())) {
	        
	        Messages message = new Messages();
	        message.setMessageContent(messageDTO.getMessage());
	        message.setTimestamp(LocalDateTime.now()); 
	        messagesRepository.save(message);


	        handleMessage.broadcastMessage(messageDTO.getGroupName(), messageDTO.getMessage(), messageDTO.getSenderName());
	    } else {
	        // Handle validation failure
	    }
	}

	public boolean validateMessages(String message) {
	    
	    return true;
	}
	
    @Override
    public boolean validateMessage(String messageContent) {
    	
    	return true;
    }
		
}

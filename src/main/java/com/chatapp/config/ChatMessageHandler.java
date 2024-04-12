package com.chatapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.chatapp.dto.MessagesDTO;
import com.chatapp.entity.Groups;
import com.chatapp.repository.GroupsRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Component
public class ChatMessageHandler extends TextWebSocketHandler {
	
	@Autowired
    private GroupsRepository groupsRepository;
	
	
    private SimpMessagingTemplate messagingTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ChatMessageHandler.class);
    // Map to store WebSocket sessions by community name
    private final Map<String, Set<WebSocketSession>> groupSessions = new HashMap<>();
    // ObjectMapper for converting between Java objects and JSON
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String groupName = getGroupName(session);
        groupSessions.computeIfAbsent(groupName, k -> new HashSet<>()).add(session);
        logger.info("Session added to group: " + groupName);
    }


    private String getGroupName(WebSocketSession session) {
        String path = session.getUri().getPath();
        String groupName = path.substring(path.lastIndexOf('/') + 1); 
        logger.info("Extracted group name: {}", groupName);
        return groupName;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String sessionGroupName = getGroupName(session); 
        JsonNode jsonNode = new ObjectMapper().readTree(message.getPayload());
        String messageContent = jsonNode.path("message").asText();
        String senderName = jsonNode.path("senderName").asText();
        String messageGroupName = jsonNode.path("groupName").asText(); // Group name from message payload

        // Verify that the group exists in the database before proceeding
        Optional<Groups> group = groupsRepository.findByGroupName(messageGroupName);
        if (group.isPresent()) {
            broadcastMessage(messageGroupName, messageContent, senderName); // Use groupName from message
        } else {
            logger.error("Group {} does not exist.", messageGroupName);
            session.sendMessage(new TextMessage("Group does not exist."));
        }
    }

    public void broadcastMessage(String groupName, String message, String senderName) {
        Set<WebSocketSession> sessions = groupSessions.get(groupName);
        if (sessions != null) {
            sessions.forEach(s -> {
                try {
                    if (s.isOpen()) {
                        s.sendMessage(new TextMessage("Sender: " + senderName + ", Message: " + message));
                    }
                } catch (IOException e) {
                    logger.error("Error sending message to group {}: {}", groupName, e.getMessage());
                }
            });
        } else {
            logger.error("No active sessions for group {}", groupName);
        }
    }



     private void handleCrossGroupsMessage(MessagesDTO messageDTO) {
        logger.info("Routing cross-groups message to {}", messageDTO.getGroupName());
        broadcastMessage(messageDTO.getGroupName(), messageDTO.getMessage(), messageDTO.getSenderName());
    }

        private boolean isCrossGroupMessage(String message) {
        return message.contains(":");
    }
}

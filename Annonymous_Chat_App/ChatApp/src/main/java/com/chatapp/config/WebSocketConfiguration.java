package com.chatapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatMessageHandler(), "/chat/{groupName}").setAllowedOrigins("*");
        registry.addHandler(chatMessageHandler(), "/chat/{groupName}/sendmessage/*").setAllowedOrigins("*");
    }

    @Bean
    public ChatMessageHandler chatMessageHandler() {
        return new ChatMessageHandler();
    }
}

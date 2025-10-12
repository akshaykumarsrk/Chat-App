package com.substring.chat.chat_app_backend.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer
{
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {

        // it enables simple in memory message broker
        // when messages comes from server, it will be published on /topic prefix
        // example-: if client subscribe /topic/messages, so server can send the messages on this route
        // example-: if client subscribe /test/messages, so server cannot send the messages on this route
        // because /topic is not prefix
        config.enableSimpleBroker("/topic");

        // it set the prefix for those messages which has to handle by server side controller
        // if client send message on this route /app/chat, server side controller method which we will make
        // to handle this, will annotate with @MessagingMapping("/chat")
        // server-side: @MessagingMapping("/chat")
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        // client establish connection or subscribe on this end point /chat
        // after that message will come to @MessageMapping sendMessage but it will prefix by /app
        // that is /app/sendMessage/roomId
        // and after this, that banda who subscribe the message on /topic/room/roomId
        registry.addEndpoint("/chat")  // connection establishment
                .setAllowedOrigins("http://localhost:3000")
                .withSockJS(); // SockJS library for fallback if webSocket not work for any reason like due to old browser
    }
}

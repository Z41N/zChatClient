package com.z41n.chatClient.controllers;

import com.z41n.chatClient.ChatPayload;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage") // URL we want to use to invoke this function
    @SendTo("/topic/public")
    public ChatPayload sendMessage(@Payload ChatPayload payload) {
        return payload;
    }

    /***
     * Establish a connection between the user and the websocket
     */
    @MessageMapping("/chat.addUser") // URL we want to use to invoke this function
    @SendTo("/topic/public")
    public ChatPayload addUser(@Payload ChatPayload payload, SimpMessageHeaderAccessor headerAccessor) {
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("username", payload.getSender());
        return payload;
    }

}

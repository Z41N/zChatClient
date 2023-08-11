package com.z41n.chatClient.listeners;

import com.z41n.chatClient.ChatPayload;
import com.z41n.chatClient.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j

public class WebSocketEventListener {

    private final SimpMessageSendingOperations messageTemplate;

    @EventListener
    public void handleWebSocketDisconnect(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("userName");
        if (username != null) {
            log.info("{} has been disconnected.", username);

            ChatPayload chatPayload = ChatPayload.builder()
                    .mMessageType(MessageType.LEAVE)
                    .mSenderName(username)
                    .build();

            messageTemplate.convertAndSend("/topic/public", chatPayload);
        } else {
            //
        }
    }

}

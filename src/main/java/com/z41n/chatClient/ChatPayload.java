package com.z41n.chatClient;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatPayload {

    private MessageType type;
    private String content;
    private String sender;



}

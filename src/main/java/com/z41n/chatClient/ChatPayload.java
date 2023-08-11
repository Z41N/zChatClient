package com.z41n.chatClient;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatPayload {

    private String mMessageContent;
    private String mSenderName;
    private MessageType mMessageType;



}

package com.immate.immate.domain.chat.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Document(collection = "chat_messages")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    @Id
    private String id;
    
    private String crewName;
    private String senderId;
    private String senderName;
    private String content;
    private LocalDateTime timestamp;
    
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
} 
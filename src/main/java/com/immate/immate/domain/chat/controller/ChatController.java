package com.immate.immate.domain.chat.controller;

import com.immate.immate.domain.chat.entity.ChatMessage;
import com.immate.immate.domain.chat.service.ChatService;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @MessageMapping("/chat.crew/{crewName}")
    @SendTo("/topic/crew/{crewName}")
    public ChatMessage sendMessage(@DestinationVariable String crewName, 
                                 @Payload ChatMessage chatMessage,
                                 @Header("userId") String userId) {
        return chatService.saveAndSendMessage(crewName, chatMessage, userId);
    }

    @GetMapping("/api/chat/{crewName}/history")
    public ResponseEntity<List<ChatMessage>> getChatHistory(@PathVariable String crewName) {
        return ResponseEntity.ok(chatService.getChatHistory(crewName));
    }

    @DeleteMapping("/api/chat/message/{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable String messageId) {
        chatService.deleteMessage(messageId);
        return ResponseEntity.ok().build();
    }
} 
package com.immate.immate.domain.chat.service;

import com.immate.immate.domain.chat.entity.ChatMessage;
import com.immate.immate.domain.chat.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatMessageRepository chatMessageRepository;

    public ChatMessage saveAndSendMessage(String crewName, ChatMessage chatMessage, String userId) {
        chatMessage.setCrewName(crewName);
        chatMessage.setSenderId(userId);
        return chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> getChatHistory(String crewName) {
        return chatMessageRepository.findTop100ByCrewNameOrderByTimestampDesc(crewName);
    }

    public void deleteMessage(String messageId) {
        chatMessageRepository.deleteById(messageId);
    }
} 
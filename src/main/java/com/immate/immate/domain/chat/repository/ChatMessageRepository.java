package com.immate.immate.domain.chat.repository;

import com.immate.immate.domain.chat.entity.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findByCrewNameOrderByTimestampDesc(String crewName);
    List<ChatMessage> findByCrewNameAndTimestampAfterOrderByTimestamp(String crewName, LocalDateTime after);
    List<ChatMessage> findTop100ByCrewNameOrderByTimestampDesc(String crewName);
} 
package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.ChatLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfChatLogRepository extends JpaRepository<ChatLog, Long> {
}

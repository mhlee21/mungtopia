package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.ChatLog;
import com.d209.mungtopia.entity.ChatRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfChatLogRepository extends JpaRepository<ChatLog, Long> {

    @Query ("select c from ChatLog c where c.chatRoom = :chatRoom order by c.createtime")
    Page<ChatLog> chatLogList(@Param("chatRoom") ChatRoom chatRoom, Pageable pageable);
}

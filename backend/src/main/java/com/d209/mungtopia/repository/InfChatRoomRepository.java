package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}

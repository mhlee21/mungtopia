package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ChatRoomRepository {
    EntityManager em;

    public ChatRoom find(Long chatRoomId){
        return em.find(ChatRoom.class, chatRoomId);
    }
}

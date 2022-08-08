package com.d209.mungtopia.repository;

import com.d209.mungtopia.entity.ChatLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ChatLogRepository {
    EntityManager em;

    public void findChatLogList(Long chatRoomId, int pageNo){
        em.createQuery("select c from ChatLog c where c.chatRoom.chatRoomId =: chatRoomId order by c.createtime desc", ChatLog.class)
                .setParameter("chatRoomId", chatRoomId)
                .setFirstResult((pageNo - 1) * 15)
                .setMaxResults(15)
                .getResultList();
    }
}

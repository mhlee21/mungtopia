package com.d209.mungtopia.entity;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Table(name = "chat_log", schema = "mungtopia", catalog = "")
public class ChatLog {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "chat_log_id")
    private long chatLogId;
    @Basic
    @Column(name = "user_nickname")
    private String userNickname;
    @Basic
    @Column(name = "chat_content")
    private String chatContent;
    @Basic
    @Column(name = "createtime")
    private Timestamp createtime;
    @Basic
    @Column(name = "chat_context")
    private String chatContext;

    @ManyToOne
    @JoinColumn(name = "chat_room_id", referencedColumnName = "chat_room_id", nullable = false)
    private ChatRoom chatRoomByChatRoomId;

    public ChatRoom getChatRoomByChatRoomId() {
        return chatRoomByChatRoomId;
    }

    public void setChatRoomByChatRoomId(ChatRoom chatRoomByChatRoomId) {
        this.chatRoomByChatRoomId = chatRoomByChatRoomId;
    }
}

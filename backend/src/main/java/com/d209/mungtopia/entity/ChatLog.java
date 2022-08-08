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
    private Long chatLogId;

    @Column(name = "user_seq")
    private Long userSeq;

    @Column(name = "chat_content")
    private String chatContent;

    @Column(name = "createtime")
    private Timestamp createtime;

    @ManyToOne
    @JoinColumn(name = "chat_room_id", referencedColumnName = "chat_room_id", nullable = false)
    private ChatRoom chatRoom;

}

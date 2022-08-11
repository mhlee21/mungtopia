package com.d209.mungtopia.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chat_log", schema = "mungtopia")
public class ChatLog {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "chat_log_id")
    private Long chatLogId;

    @Column(name = "user_seq")
    private Long userSeq;

    @Column(name = "content")
    private String content;

    @Column(name = "createtime")
    private Timestamp createtime;

    @Column(name = "is_request")
    private Boolean isRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id", referencedColumnName = "chat_room_id", nullable = false)
    private ChatRoom chatRoom;
}

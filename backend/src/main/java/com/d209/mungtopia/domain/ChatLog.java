package com.d209.mungtopia.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class ChatLog {
    @Id
    @GeneratedValue
    private Long chatLogId;
    private String user_nickname;
    private String chat_context;
    private LocalDateTime createtime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_log_id")
    private ChatRoom chatRoom;
}

package com.d209.mungtopia.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id")
    private Long chatRoomId;

    private Long protectorId;
    private Long applicationId;
    private String protectorNickname;
    private String applicationNickname;
    private LocalDateTime createtime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adoption_process_id")
    private AdoptionProcess adoptionProcess;
}

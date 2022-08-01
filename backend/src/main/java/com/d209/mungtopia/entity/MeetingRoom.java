package com.d209.mungtopia.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class MeetingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meeting_room_id")
    private Long meetingRoomId;
    private Long hostId;
    private boolean active;
    private LocalDateTime createTime;
}

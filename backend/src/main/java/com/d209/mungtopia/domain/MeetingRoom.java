package com.d209.mungtopia.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
public class MeetingRoom {
    @Id
    @GeneratedValue
    private Long meetingRoomId;
    private Long hostId;
    private boolean active;
    private LocalDateTime createTime;
}

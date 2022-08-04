package com.d209.mungtopia.entity;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Table(name = "meeting_room", schema = "mungtopia", catalog = "")
public class MeetingRoom {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "meeting_room_id")
    private long meetingRoomId;

    @Column(name = "host_id")
    private Long hostId;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "createtime")
    private Timestamp createtime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adoption_process_id", referencedColumnName = "adoption_process_id", nullable = false)
    private AdoptionProcess adoptionProcess;
}

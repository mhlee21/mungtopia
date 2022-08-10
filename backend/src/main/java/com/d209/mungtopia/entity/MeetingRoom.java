package com.d209.mungtopia.entity;

import io.openvidu.java.client.Session;
import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Table(name = "meeting_room", schema = "mungtopia")
public class MeetingRoom {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "meeting_room_id")
    private long meetingRoomId;

    @Column(name = "session_name")
    private String meetingName;

//    @Column(name = "session")
//    private Ob session;


//    @Column(name = "active")
//    private Boolean active;

//    @Column(name = "createtime")
//    private Timestamp createtime;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "adoption_process_id", referencedColumnName = "adoption_process_id", nullable = false)
//    private AdoptionProcess adoptionProcess;
}

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
    @Basic
    @Column(name = "host_id")
    private Long hostId;
    @Basic
    @Column(name = "active")
    private Byte active;
    @Basic
    @Column(name = "createtime")
    private Timestamp createtime;
    @Basic
    @Column(name = "create_time")
    private Timestamp createTime;

    @ManyToOne
    @JoinColumn(name = "adoption_process_id", referencedColumnName = "adoption_process_id", nullable = false)
    private AdoptionProcess adoptionProcessByAdoptionProcessId;


    public AdoptionProcess getAdoptionProcessByAdoptionProcessId() {
        return adoptionProcessByAdoptionProcessId;
    }

    public void setAdoptionProcessByAdoptionProcessId(AdoptionProcess adoptionProcessByAdoptionProcessId) {
        this.adoptionProcessByAdoptionProcessId = adoptionProcessByAdoptionProcessId;
    }
}

package com.d209.mungtopia.entity;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Table(name = "chat_room", schema = "mungtopia", catalog = "")
public class ChatRoom {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "chat_room_id")
    private long chatRoomId;
    @Basic
    @Column(name = "protector_id")
    private Long protectorId;
    @Basic
    @Column(name = "applicant_id")
    private Long applicantId;
    @Basic
    @Column(name = "protector_nickname")
    private String protectorNickname;
    @Basic
    @Column(name = "applicant_nickname")
    private String applicantNickname;
    @Basic
    @Column(name = "createtime")
    private Timestamp createtime;
    @Basic
    @Column(name = "application_id")
    private Long applicationId;
    @Basic
    @Column(name = "application_nickname")
    private String applicationNickname;

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

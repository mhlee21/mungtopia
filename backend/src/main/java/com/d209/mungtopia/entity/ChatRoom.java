package com.d209.mungtopia.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "chat_room", schema = "mungtopia")
public class ChatRoom {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "chat_room_id")
    private Long chatRoomId;

    @Column(name = "protector_id")
    private Long protectorId;

    @Column(name = "applicant_id")
    private Long applicantId;

    @Column(name = "protector_nickname")
    private String protectorNickname;

    @Column(name = "applicant_nickname")
    private String applicantNickname;

    @Column(name = "createtime")
    private Timestamp createtime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adoption_process_id", referencedColumnName = "adoption_process_id")
    private AdoptionProcess adoptionProcess;

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatLog> chatLogList;

    public void setAdoptionProcess(AdoptionProcess adoptionProcess) {
        this.adoptionProcess = adoptionProcess;
    }
}

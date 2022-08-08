package com.d209.mungtopia.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "adoption_process", schema = "mungtopia", catalog = "")
@Getter
public class AdoptionProcess {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "adoption_process_id")
    private Long adoptionProcessId;

    @Column(name = "step")
    private Integer step;

    @Column(name = "step_status")
    private Boolean stepStatus;

    @OneToOne
    @JoinColumn(name = "application_id", referencedColumnName = "application_id", nullable = false)
    private Application application;

    @OneToOne(mappedBy = "adoptionProcess", fetch = FetchType.LAZY)
    private MeetingRoom meetingRoom;

    @OneToOne(mappedBy = "adoptionProcess", fetch = FetchType.LAZY)
    private ChatRoom chatRoom;

    @OneToMany(mappedBy = "adoptionProcess", cascade = CascadeType.ALL)
    private List<AdoptionStepDate> adoptionStepDateList;

    // == 비즈니스 로직 ==
    public void setStep(int step){
        this.step = step;
    }

    public void setStepStatus(boolean stepStatus){
        this.stepStatus = stepStatus;
    }
}

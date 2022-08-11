package com.d209.mungtopia.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "adoption_process", schema = "mungtopia")
@Getter
public class AdoptionProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adoption_process_id")
    private Long adoptionProcessId;

    @Column(name = "step", nullable = false)
    private int step;

    @Column(name = "step_status")
    private Boolean stepStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", referencedColumnName = "application_id", nullable = false)
    private Application application;

//    @OneToOne(mappedBy = "adoptionProcess", fetch = FetchType.LAZY)
//    private MeetingRoom meetingRoom;

    @OneToOne(mappedBy = "adoptionProcess", fetch = FetchType.LAZY)
    private ChatRoom chatRoom;

    @OneToMany(mappedBy = "adoptionProcess")
    // Cascade.All 넣으면 delete 작동 안함!!!!!
    @JsonBackReference
    private List<AdoptionStepDate> adoptionStepDateList;


    // == 비즈니스 로직 ==
    public void setStep(int step){
        this.step = step;
    }

    public void setStepStatus(boolean stepStatus){
        this.stepStatus = stepStatus;
    }
}

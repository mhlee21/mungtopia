package com.d209.mungtopia.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "adoption_step_date", schema = "mungtopia")
public class AdoptionStepDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adoption_step_date_id")
    private long adoptionStepDateId;

    @Column(name = "step")
    private int step;

    @Column(name = "date", nullable = true)
    private Timestamp date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adoption_process_id", referencedColumnName = "adoption_process_id")
    @JsonManagedReference
    private AdoptionProcess adoptionProcess;

    public void changeDate(Timestamp date){
        this.date = date;
    }

    public AdoptionStepDate(int step, AdoptionProcess adoptionProcess){
        this.step = step;
        this.adoptionProcess = adoptionProcess;
    }

    public AdoptionStepDate(int step, Timestamp date){
        this.step = step;
        this.date = date;
    }

    public void setAdoptionProcess(AdoptionProcess adoptionProcess) {
        this.adoptionProcess = adoptionProcess;
    }
}

package com.d209.mungtopia.entity;

import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "adoption_step_date", schema = "mungtopia")
@Getter
public class AdoptionStepDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adoption_step_date_id")
    private long adoptionStepDateId;

    @Column(name = "step")
    private int step;

    @Column(name = "date", nullable = true)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "adoption_process_id", referencedColumnName = "adoption_process_id", nullable = false)
    private AdoptionProcess adoptionProcess;

    public void changeDate(LocalDateTime localDateTime){
        this.date = date;
    }
}

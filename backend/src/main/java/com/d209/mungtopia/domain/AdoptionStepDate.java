package com.d209.mungtopia.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
public class AdoptionStepDate {
    @Id
    @GeneratedValue
    private Long adoptionStepDateId;
    private Integer step;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adoption_step_date_id")
    private AdoptionProcess adoptionProcess;
}

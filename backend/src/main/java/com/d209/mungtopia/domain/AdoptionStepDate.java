package com.d209.mungtopia.domain;

import lombok.Getter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
public class AdoptionStepDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adoption_step_date_id")
    private Long adoptionStepDateId;
    private Integer step;

    @Column(name = "date", columnDefinition = "TIMESTAMP")
    @Type(type = "org.hibernate.type.TimestampType")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adoption_process_id")
    private AdoptionProcess adoptionProcess;
}

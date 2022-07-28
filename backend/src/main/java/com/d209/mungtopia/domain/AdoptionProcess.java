package com.d209.mungtopia.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class AdoptionProcess {
    @Id
    @GeneratedValue
    private Long adoptionProcessId;
    private Integer step;
    private Boolean stepStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adoption_process_id")
    private Application application;
}

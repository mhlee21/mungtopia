package com.d209.mungtopia.entity;

import lombok.Getter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Table(name = "answer", schema = "mungtopia")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private long answerId;

    @Column(name = "order")
    private int order;

    @Column(name = "answer")
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", referencedColumnName = "application_id", nullable = false)
    private Application application;
}

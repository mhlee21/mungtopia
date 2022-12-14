package com.d209.mungtopia.entity;

import com.d209.mungtopia.dto.applicant.AnswerDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "answer", schema = "mungtopia")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long answerId;

    @Column(name = "idx")
    private int idx;

    @Column(name = "answer")
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", referencedColumnName = "application_id")
    @JsonIgnore
    private Application application;

    public Answer(AnswerDto answerDto, Application application) {
        this.idx = answerDto.getIdx();
        this.answer = answerDto.getAnswer();
        this.application = application;
    }
}

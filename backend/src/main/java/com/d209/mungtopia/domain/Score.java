package com.d209.mungtopia.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Score {
    @Id
    @GeneratedValue
    private Long scoreId;
    private Integer result;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "score_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "score_id")
    private GameTag gameTag;
}

package com.d209.mungtopia.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_id")
    private Long scoreId;
    private Integer result;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_tag_id")
    private GameTag gameTag;
}

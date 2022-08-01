package com.d209.mungtopia.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    private String contents;
    private LocalDateTime createtime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_tag_id")
    private BoardTag boardTag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;
}

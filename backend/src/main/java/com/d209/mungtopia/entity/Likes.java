package com.d209.mungtopia.entity;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
public class Likes {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "like_id")
    private long likeId;

    @Column(name = "createtime")
    private Timestamp createtime;

    @ManyToOne
    @JoinColumn(name = "user_seq", referencedColumnName = "user_seq", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_id", referencedColumnName = "board_id", nullable = false)
    private Board board;
}

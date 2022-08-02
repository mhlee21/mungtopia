package com.d209.mungtopia.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Table(name = "game_result", schema = "mungtopia", catalog = "")
public class GameResult {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "score_id")
    private long scoreId;
    @Basic
    @Column(name = "game_tag")
    private String gameTag;
    @Basic
    @Column(name = "result")
    private Byte result;

    @ManyToOne
    @JoinColumn(name = "user_seq", referencedColumnName = "user_seq", nullable = false)
    private User userByUserSeq;


    public User getUserByUserSeq() {
        return userByUserSeq;
    }

    public void setUserByUserSeq(User userByUserSeq) {
        this.userByUserSeq = userByUserSeq;
    }
}

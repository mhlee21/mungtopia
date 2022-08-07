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
    private Long likeId;
    @Basic
    @Column(name = "createtime")
    private Timestamp createtime;

    @ManyToOne
    @JoinColumn(name = "user_seq", referencedColumnName = "user_seq", nullable = false)
    private User userByUserSeq;
    @ManyToOne
    @JoinColumn(name = "board_id", referencedColumnName = "board_id", nullable = false)
    private Board boardByBoardId;

    public User getUserByUserSeq() {
        return userByUserSeq;
    }

    public void setUserByUserSeq(User userByUserSeq) {
        this.userByUserSeq = userByUserSeq;
    }

    public Board getBoardByBoardId() {
        return boardByBoardId;
    }

    public void setBoardByBoardId(Board boardByBoardId) {
        this.boardByBoardId = boardByBoardId;
    }
}

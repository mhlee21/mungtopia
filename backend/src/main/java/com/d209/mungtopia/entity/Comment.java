package com.d209.mungtopia.entity;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
public class Comment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "comment_id")
    private long commentId;
    @Basic
    @Column(name = "user_seq")
    private Long userSeq;
    @Basic
    @Column(name = "user_nickname")
    private String userNickname;
    @Basic
    @Column(name = "contents")
    private String contents;
    @Basic
    @Column(name = "secret")
    private Byte secret;
    @Basic
    @Column(name = "createtime")
    private Timestamp createtime;

    @ManyToOne
    @JoinColumn(name = "board_id", referencedColumnName = "board_id", nullable = false)
    private Board boardByBoardId;

    public Board getBoardByBoardId() {
        return boardByBoardId;
    }

    public void setBoardByBoardId(Board boardByBoardId) {
        this.boardByBoardId = boardByBoardId;
    }
}

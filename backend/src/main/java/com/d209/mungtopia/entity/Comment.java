package com.d209.mungtopia.entity;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
public class Comment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "comment_id")
    private long commentId;

    @Column(name = "user_seq")
    private Long userSeq;

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(name = "contents")
    private String contents;

    @Column(name = "secret")
    private boolean secret;

    @Column(name = "createtime")
    private Timestamp createtime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", referencedColumnName = "board_id", nullable = false)
    private Board board;

    @OneToMany(mappedBy = "comment")
    private List<Reply> replyList;

}

package com.d209.mungtopia.entity;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
public class Reply {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "reply_id")
    private Long replyId;

    @Column(name = "user_seq")
    private Long userSeq;

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(name = "content")
    private String content;

    @Column(name = "createtime")
    private Timestamp createtime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", referencedColumnName = "comment_id", nullable = false)
    private Comment comment;
}

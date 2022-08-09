package com.d209.mungtopia.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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

    @Column(name = "contents")
    private String content;

    @Column(name = "createtime")
    private Timestamp createtime;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", referencedColumnName = "comment_id", nullable = false)
    @JsonIgnore
//    아래 두개 다 실패함
//    @JsonManagedReference // 순환 참조 방지
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "commentId")
    private Comment comment;
}

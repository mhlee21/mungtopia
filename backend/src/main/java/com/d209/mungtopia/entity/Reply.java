package com.d209.mungtopia.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Column(name = "secret")
    private boolean secret;

    @Column(name = "createtime")
    private Timestamp createtime;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", referencedColumnName = "comment_id", nullable = false)
    @JsonIgnore
//    아래 두개 다 실패함
//    @JsonManagedReference // 순환 참조 방지
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "commentId")
    private Comment comment;

    public void setContent(String content) {
        this.content = content;
    }

    public void setSecret(boolean secret) {
        this.secret = secret;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }
}

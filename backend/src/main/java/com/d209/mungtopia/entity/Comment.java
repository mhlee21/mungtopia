package com.d209.mungtopia.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @JsonIgnore
    private Board board;

    @OneToMany(mappedBy = "comment")
//    이거 아님
//    @JsonBackReference //순환 참조 방지
    private List<Reply> replyList;

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setSecret(boolean secret) {
        this.secret = secret;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }
}

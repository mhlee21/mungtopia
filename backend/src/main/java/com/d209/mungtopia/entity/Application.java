package com.d209.mungtopia.entity;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
public class Application {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "application_id")
    private long applicationId;
    @Basic
    @Column(name = "board_id")
    private Long boardId;
    @Basic
    @Column(name = "send")
    private Byte send;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "birth")
    private String birth;
    @Basic
    @Column(name = "gender")
    private String gender;
    @Basic
    @Column(name = "job")
    private String job;
    @Basic
    @Column(name = "zonecode")
    private String zonecode;
    @Basic
    @Column(name = "road_address")
    private String roadAddress;
    @Basic
    @Column(name = "detail_address")
    private String detailAddress;
    @Basic
    @Column(name = "etc")
    private String etc;
    @Basic
    @Column(name = "createtime")
    private Timestamp createtime;
    @Basic
    @Column(name = "application_status")
    private String applicationStatus;

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

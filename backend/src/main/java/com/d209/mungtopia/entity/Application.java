package com.d209.mungtopia.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class Application {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "application_id")
    private Long applicationId;

    @Column(name = "board_id")
    private Long boardId;

    @Column(name = "send")
    private Boolean send;

    @Column(name = "name")
    private String name;

    @Column(name = "birth")
    private String birth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "job")
    private String job;

    @Column(name = "zonecode")
    private String zonecode;

    @Column(name = "road_address")
    private String roadAddress;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name = "etc")
    private String etc;

    @Column(name = "createtime")
    private LocalDateTime createtime;

    @Column(name = "application_status")
    private Integer applicationStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "user_seq", referencedColumnName = "user_seq", nullable = false)
    private User user;

    @OneToMany(mappedBy = "application")
    private List<Answer> answerList;

    @OneToOne(mappedBy = "application", fetch = FetchType.LAZY)
    private AdoptionProcess adoptionProcess;

    public void setApplicationStatus(Integer applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
}

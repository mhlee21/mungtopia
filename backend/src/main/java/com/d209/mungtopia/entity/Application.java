package com.d209.mungtopia.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "application", schema = "mungtopia")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long applicationId;

    @Column(name = "board_id")
    private Long boardId;

    @Column(name = "send")
    private Boolean send;

    @Column(name = "name")
    private String name;

    @Embedded
    private UserInfo userInfo;

    @Column(name = "createtime")
    private LocalDateTime createtime;

    @Column(name = "application_status")
    private Integer applicationStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "user_seq", referencedColumnName = "user_seq", nullable = false)
    private User user;

    @OneToMany(mappedBy = "application", fetch = FetchType.LAZY)
    private List<Answer> answerList;

    @OneToOne(mappedBy = "application", fetch = FetchType.LAZY)
    private AdoptionProcess adoptionProcess;

    public void changeApplicationStatus(Integer applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }
}

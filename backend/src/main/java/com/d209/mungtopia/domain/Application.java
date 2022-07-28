package com.d209.mungtopia.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Application {
    @Id
    @GeneratedValue
    private Long applicationId;

    private boolean send;
    private Integer applicationStatus;
    private Long boardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private User user;

    @Embedded
    private UserInfo userInfo;
}

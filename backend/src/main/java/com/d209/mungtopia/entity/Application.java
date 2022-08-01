package com.d209.mungtopia.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long applicationId;

    private boolean send;
    private Integer applicationStatus;
    private Long boardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @Embedded
    private MemberInfo memberInfo;
}

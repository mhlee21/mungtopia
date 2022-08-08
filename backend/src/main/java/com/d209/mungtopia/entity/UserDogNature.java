package com.d209.mungtopia.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Table(name = "user_dog_nature", schema = "mungtopia", catalog = "")
public class UserDogNature {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_dog_nature_id")
    private long userDogNatureId;

    @Column(name = "nature1")
    private int nature1;

    @Column(name = "nature2")
    private int nature2;

    @Column(name = "nature3")
    private int nature3;

    @Column(name = "nature4")
    private int nature4;

    @Column(name = "nature5")
    private int nature5;

    @Column(name = "nature6")
    private int nature6;

    // referencedColumnName : 외래 키가 참조하는 대상 테이블의 컬럼명
    @OneToOne
    @JoinColumn(name = "user_seq", referencedColumnName = "user_seq", nullable = false)
    private User user;
}

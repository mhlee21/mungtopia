package com.d209.mungtopia.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    private int status;
    private String email;
    private String nickname;
    private String profile;

    @Embedded
    private MemberInfo userinfo;

    @OneToMany(mappedBy = "member")
    private List<Like> likeList;

    @OneToMany(mappedBy = "member")
    private List<Star> starList;
    private LocalDateTime createtime;

}

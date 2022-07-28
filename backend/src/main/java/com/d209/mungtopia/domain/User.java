package com.d209.mungtopia.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class User {
    @Id @GeneratedValue
    private Long userId;

    private int status;
    private String email;
    private String nickname;
    private String profile;

    @Embedded
    private UserInfo userinfo;

    @OneToMany(mappedBy = "user")
    private List<Like> LikeList;

    @OneToMany(mappedBy = "user")
    private List<Star> LikeStar;
    private LocalDateTime createtime;

}

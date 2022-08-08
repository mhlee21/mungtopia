package com.d209.mungtopia.entity;

import com.d209.mungtopia.oauth.entity.ProviderType;
import com.d209.mungtopia.oauth.entity.RoleType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString(exclude = "Application")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_seq")
    private long userSeq;

    @Column(name = "status")
    private Integer status;

    @Column(name = "email")
    private String email;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email_verified_yn")
    private String emailVerifiedYn;

    @Column(name = "password")
    private String password;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "provider_type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private ProviderType providerType;

    @Basic
    @Column(name = "role_type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleType roleType;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "username")
    private String username;

    @Embedded
    private UserInfo userInfo;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "user")
//    @JsonBackReference
    private List<Application> applicationList;

    @OneToMany(mappedBy = "user")
    private List<GameResult> gameResultList;

    @OneToOne(mappedBy = "user")
    private UserDogNature userDogNature;

    @OneToMany(mappedBy = "user")
    private List<Likes> likesList;

    @OneToMany(mappedBy = "user")
    private List<Star> starList;

    @OneToMany(mappedBy = "user")
    private List<Board> boardList;

    public User(
            @NotNull @Size(max = 64) String userId,
            @NotNull @Size(max = 100) String username,
            @NotNull @Size(max = 512) String email,
            @NotNull @Size(max = 1) String emailVerifiedYn,
            @NotNull @Size(max = 512) String profileImageUrl,
            @NotNull ProviderType providerType,
            @NotNull RoleType roleType,
            @NotNull LocalDateTime createdAt,
            @NotNull LocalDateTime modifiedAt
    ) {
        this.userId = userId;
        this.username = username;
        this.password = "NO_PASS";
        this.email = email != null ? email : "NO_EMAIL";
        this.emailVerifiedYn = emailVerifiedYn;
        this.profileImageUrl = profileImageUrl != null ? profileImageUrl : "";
        this.providerType = providerType;
        this.roleType = roleType;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.status = 1; // 회원가입시 1, 탈퇴시 0
    }
}

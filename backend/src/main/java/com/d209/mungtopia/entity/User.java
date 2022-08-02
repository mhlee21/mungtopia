package com.d209.mungtopia.entity;

import com.d209.mungtopia.oauth.entity.ProviderType;
import com.d209.mungtopia.oauth.entity.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_seq")
    private long userSeq;
    @Basic
    @Column(name = "status")
    private Integer status;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "nickname")
    private String nickname;
    @Basic
    @Column(name = "email_verified_yn")
    private String emailVerifiedYn;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Basic
    @Column(name = "provider_type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private ProviderType providerType;
    @Basic
    @Column(name = "role_type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleType roleType;

    @Basic
    @Column(name = "user_id")
    private String userId;
    @Basic
    @Column(name = "username")
    private String username;
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
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Basic
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;


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
    }

}

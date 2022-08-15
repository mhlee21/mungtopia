package com.d209.mungtopia.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
@Getter
@Setter
public class UserInfo {
    @Transient
    private String name;

    @Transient
    private String profile;

    @Transient
    private String nickname;

    @Transient
    private String email;

    // 번호만 저장
    @Column(name = "phone_number", length = 11)
    private String phoneNumber;

    @Column(name = "birth")
    private String birth;

    @Column(name = "gender", length = 1)
    private String gender;

    @Column(name = "job")
    private String job;

    @Column(name = "zonecode", length = 5)
    private String zonecode;

    @Column(name = "road_address")
    private String roadAddress;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name = "etc")
    private String etc;

    protected UserInfo(){

    }

    public UserInfo(String phoneNumber,
                    String birth,
                    String gender,
                    String job,
                    String zonecode,
                    String roadAddress,
                    String detailAddress,
                    String etc) {
        this.phoneNumber = phoneNumber;
        this.birth = birth;
        this.gender = gender;
        this.job = job;
        this.zonecode = zonecode;
        this.roadAddress = roadAddress;
        this.detailAddress = detailAddress;
        this.etc = etc;
    }

    public UserInfo(String name,
                    String gender,
                    String birth,
                    String phoneNumber,
                    String job,
                    String zonecode,
                    String roadAddress,
                    String detailAddress,
                    String etc) {
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.job = job;
        this.zonecode = zonecode;
        this.roadAddress = roadAddress;
        this.detailAddress = detailAddress;
        this.etc = etc;
    }

    public void changeName(String name){
        this.name = name;
    }

    public void setInfo(String nickname, String profile, String email, String name){
        this.nickname = nickname;
        this.profile = profile;
        this.email = email;
        this.name = name;
    }
}

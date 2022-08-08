package com.d209.mungtopia.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class UserInfo {
    // 번호만 저장
    @Column(name = "phone_number", length = 11)
    private String phoneNumber;

    @Column(name = "birth")
    private String birth;

    @Column(name = "gender", length = 1)
    private Character gender;

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
                    Character gender,
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
}

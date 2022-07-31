package com.d209.mungtopia.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class MemberInfo {
    private String name;
    private String birth;
    private char gender;
    private String job;
    private String zonecode;
    private String road_address;
    private String detail_address;
    private String etc;
}

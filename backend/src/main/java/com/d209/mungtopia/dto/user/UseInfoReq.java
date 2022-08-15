package com.d209.mungtopia.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UseInfoReq {
    private String name;
    private String gender;
    private String birth;
    private String phoneNumber;
    private String job;
    private String zonecode;
    private String roadAddress;
    private String detailAddress;
}

package com.d209.mungtopia.dto.manage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ApplicationDto {
    private String name;
    private String birth;
    private String gender;
    private String job;
    private String zonecode;
    private String roadAddress;
    private String detailAddress;
    private String etc;
    private String createtime;
    private Integer applicationStatus;
    private List<AnswerDto> answer;

    public ApplicationDto(String name, String birth, String gender, String job, String zonecode, String roadAddress, String detailAddress, String etc, String createtime, Integer applicationStatus) {
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.job = job;
        this.zonecode = zonecode;
        this.roadAddress = roadAddress;
        this.detailAddress = detailAddress;
        this.etc = etc;
        this.createtime = createtime;
        this.applicationStatus = applicationStatus;
    }
}

package com.d209.mungtopia.dto.manage;

import com.d209.mungtopia.entity.UserInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ApplicationDto {
//    private String name;
    private UserInfo userInfo;
    private String createtime;
    private Integer applicationStatus;
    private List<AnswerDto> answer;

    public ApplicationDto(UserInfo userInfo, String createtime, Integer applicationStatus) {
//        this.name = name;
        this.userInfo = userInfo;
        this.createtime = createtime;
        this.applicationStatus = applicationStatus;
    }
}

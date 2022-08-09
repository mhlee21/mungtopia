package com.d209.mungtopia.dto.applicant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApplicationListInfoRes {
    private Long applicationId;
    private String dogImg;
    private String dogName; //dogName
    private Integer applicationStatus;
}

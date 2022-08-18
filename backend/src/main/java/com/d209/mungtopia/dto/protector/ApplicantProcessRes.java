package com.d209.mungtopia.dto.protector;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
public class ApplicantProcessRes {
    private int step;
    private boolean stepStatus;
    private String date;
}

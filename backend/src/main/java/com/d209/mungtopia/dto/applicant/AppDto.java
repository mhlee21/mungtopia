package com.d209.mungtopia.dto.applicant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppDto {
    private Long userSeq;
    private List<AnswerDto> applicantAnswerList = new ArrayList<>();
}

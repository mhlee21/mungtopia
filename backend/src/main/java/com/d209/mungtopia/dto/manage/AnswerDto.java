package com.d209.mungtopia.dto.manage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AnswerDto {
    private int order;
    private String answer;

    public AnswerDto(int order, String answer) {
        this.order = order;
        this.answer = answer;
    }
}

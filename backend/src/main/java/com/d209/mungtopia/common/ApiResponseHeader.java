package com.d209.mungtopia.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ApiResponseHeader {
    private int code; // 200,400, 500 코드
    private String message; // success, fail, 메소드
}

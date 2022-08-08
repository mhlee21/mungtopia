package com.d209.mungtopia.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
//@RequiredArgsConstructor
// API 서버 구성 요소 중 응답이 있는데 Response 구성하는 여러가지 방법 중
// 직접 Header, body, ErrorCode를 써서 구성하는 방법
// Header와 Body를 담아서 보낼 ApiResponse class
public class ApiResponse<T> {

    private final static int SUCCESS = 200;
    private final static int NOT_FOUND = 400;
    private final static int FAILED = 500;
    private final static String SUCCESS_MESSAGE = "SUCCESS";
    private final static String NOT_FOUND_MESSAGE = "NOT FOUND";
    private final static String FAILED_MESSAGE = "서버에서 오류가 발생하였습니다.";
    private final static String INVALID_ACCESS_TOKEN = "Invalid access token.";
    private final static String INVALID_REFRESH_TOKEN = "Invalid refresh token.";
    private final static String NOT_EXPIRED_TOKEN_YET = "Not expired token yet.";

    private ApiResponseHeader header;
    private Map<String, T> body;

    public ApiResponse(ApiResponseHeader header) {
        this.header = header;
    }

    public ApiResponse(Map<String, T> body) {
        this.body = body;
    }

    public ApiResponse(ApiResponseHeader header, Map<String, T> body) {
        this.header = header;
        this.body = body;
    }

    // 성공
    public static <T> ApiResponse<T> success(String name, T body) {
        Map<String, T> map = new HashMap<>();
        map.put(name, body);

        return new ApiResponse(new ApiResponseHeader(SUCCESS, SUCCESS_MESSAGE), map);
    }
    public static <T> ApiResponse<T> success() {
        Map<String, T> map = new HashMap<>();

        return new ApiResponse(new ApiResponseHeader(SUCCESS, SUCCESS_MESSAGE));
    }

    // 실패
    public static <T> ApiResponse<T> fail() {
        return new ApiResponse(new ApiResponseHeader(FAILED, FAILED_MESSAGE), null);
    }

    //
    public static <T> ApiResponse<T> invalidAccessToken() {
        return new ApiResponse(new ApiResponseHeader(FAILED, INVALID_ACCESS_TOKEN), null);
    }

    public static <T> ApiResponse<T> invalidRefreshToken() {
        return new ApiResponse(new ApiResponseHeader(FAILED, INVALID_REFRESH_TOKEN), null);
    }

    public static <T> ApiResponse<T> notExpiredTokenYet() {
        return new ApiResponse(new ApiResponseHeader(FAILED, NOT_EXPIRED_TOKEN_YET), null);
    }
}

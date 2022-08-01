package com.d209.mungtopia.utils;

import javax.servlet.http.HttpServletRequest;

/*
토큰이
{
	Authorization : Bearer +토큰
}
이러한 형태로 들어올 때 확인하는 Util
*/
public class HeaderUtil {

    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";

    public static String getAccessToken(HttpServletRequest request) {
        String headerValue = request.getHeader(HEADER_AUTHORIZATION);

        if (headerValue == null) {
            return null;
        }

        if (headerValue.startsWith(TOKEN_PREFIX)) {
            return headerValue.substring(TOKEN_PREFIX.length());
        }

        return null;
    }
}

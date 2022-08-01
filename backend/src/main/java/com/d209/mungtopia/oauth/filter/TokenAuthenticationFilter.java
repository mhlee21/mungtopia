package com.d209.mungtopia.oauth.filter;

import com.d209.mungtopia.oauth.token.AuthToken;
import com.d209.mungtopia.oauth.token.AuthTokenProvider;
import com.d209.mungtopia.utils.HeaderUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
// 로그인 요청시, Controller 전에 동작하게 되는 필터? - 불확실
// 토큰을 파싱하여, 유저 정보를 가지고 오고, 토큰이 유효할 경우
// 그 유저에게 권한을 부여하는 클래스
// OncePerRequestFilter 상속하여 오버라이드
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final AuthTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)  throws ServletException, IOException {

        // Header Util에서 Authorization Bearer 로 시작하는 값이 있는지 확인
        String tokenStr = HeaderUtil.getAccessToken(request);
        AuthToken token = tokenProvider.convertAuthToken(tokenStr);

        // token을 갖고와서 토큰이 맞으면 해당 유저 정보를 등록 시키고,
        // 인증된 유저 정보를 바탕으로 시큐리티에서 사용하는 토큰을 생성하고 등록
        if (token.validate()) {
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 필터 체인 - filter가 여러개 모인것
        // 요청과 응답 쌍이 체인을 통과할 때마다 컨테이너에서 호출
        // 체인을 따라서 계속 다음에 존재하는 필터로 이동
        // filterChain은 다음 필터를 가리키고 filterChain.dofilter()는 다음 필터를 호출
        // -> Spring의 나머지 필터를 수행할 수 있도록 doFilter를 적어준다.
        // 필터와 매핑된 URL 요청이 들어올때마다 doFilter()가 호출
        filterChain.doFilter(request, response);
    }

}

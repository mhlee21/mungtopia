package com.d209.mungtopia.oauth.handler;

import com.d209.mungtopia.entity.UserRefreshToken;
import com.d209.mungtopia.repository.user.UserRefreshTokenRepository;
import com.d209.mungtopia.config.properties.AppProperties;
import com.d209.mungtopia.oauth.entity.ProviderType;
import com.d209.mungtopia.oauth.entity.RoleType;
import com.d209.mungtopia.oauth.info.OAuth2UserInfo;
import com.d209.mungtopia.oauth.info.OAuth2UserInfoFactory;
import com.d209.mungtopia.oauth.repository.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.d209.mungtopia.oauth.token.AuthToken;
import com.d209.mungtopia.oauth.token.AuthTokenProvider;
import com.d209.mungtopia.utils.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import static com.d209.mungtopia.oauth.repository.OAuth2AuthorizationRequestBasedOnCookieRepository.REDIRECT_URI_PARAM_COOKIE_NAME;
import static com.d209.mungtopia.oauth.repository.OAuth2AuthorizationRequestBasedOnCookieRepository.REFRESH_TOKEN;

@Component
@RequiredArgsConstructor
// 인증이 성공하면 Spring Security는 SecurityConfig에 구성된
// OAuth2AuthenticationSuccessHandler의 onAuthenticationSuccess() 메소드를 호출
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private final AuthTokenProvider tokenProvider;
    @Autowired
    private final AppProperties appProperties;
    @Autowired
    private final UserRefreshTokenRepository userRefreshTokenRepository;
    @Autowired
    private final OAuth2AuthorizationRequestBasedOnCookieRepository authorizationRequestRepository;

    @Override
    // 몇가지 유효성 검사를 수행하고, JWT 인증 토큰을 만들고, 쿼리 문자열에 추가된 JWT 토큰을 사용하여
    // 클라이언트가 지정한 redirect_uri로 사용자를 리디렉션
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("request = " + request);
        System.out.println("response = " + response);
        System.out.println("authentication = " + authentication);
        Optional<String> redirectUri = CookieUtil.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue);
        System.out.println("BEFORE!!!! :::: =========== determineTargetUrl = " + redirectUri);
        String targetUrl = determineTargetUrl(request, response, authentication);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Optional<String> redirectUri = CookieUtil.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue);
        System.out.println("BEFORE :::: =========== redirectUri = " + redirectUri);

        if(redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            System.out.println("redirectUri = " + redirectUri);
            System.out.println("redirectUri.isPresent() = " + redirectUri.isPresent());
            System.out.println("redirectUri = " + redirectUri.get());
            throw new IllegalArgumentException("Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication");
        }
        System.out.println("AFTER :::: =========== redirectUri = " + redirectUri);
        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

        OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
        ProviderType providerType = ProviderType.valueOf(authToken.getAuthorizedClientRegistrationId().toUpperCase());
        // Odic - OpenIDConnect - 내부적으로 인증로직이 돌고, 토큰을 바로 반환해주고 이토큰으로 로그인을 처리하고 싶을 때 사용
        OidcUser user = ((OidcUser) authentication.getPrincipal());
        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(providerType, user.getAttributes());
        Collection<? extends GrantedAuthority> authorities = ((OidcUser) authentication.getPrincipal()).getAuthorities();

        RoleType roleType = hasAuthority(authorities, RoleType.ADMIN.getCode()) ? RoleType.ADMIN : RoleType.USER;

        Date now = new Date();
        AuthToken accessToken = tokenProvider.createAuthToken(
                userInfo.getId(),
                roleType.getCode(),
                new Date(now.getTime() + appProperties.getAuth().getTokenExpiry())
        );

        // refresh 토큰 설정
        long refreshTokenExpiry = appProperties.getAuth().getRefreshTokenExpiry();

        AuthToken refreshToken = tokenProvider.createAuthToken(
                appProperties.getAuth().getTokenSecret(),
                new Date(now.getTime() + refreshTokenExpiry)
        );
        System.out.println("=============== refresh 토큰 저장 ========= refreshTokenExpiry = " + refreshTokenExpiry);
        // DB 저장
        UserRefreshToken userRefreshToken = userRefreshTokenRepository.findByUserId(userInfo.getId());
        if (userRefreshToken != null) {
            userRefreshToken.setRefreshToken(refreshToken.getToken());
        } else {
            userRefreshToken = new UserRefreshToken(userInfo.getId(), refreshToken.getToken());
            userRefreshTokenRepository.saveAndFlush(userRefreshToken);
        }
        System.out.println("=============== DB 저장 ========= userRefreshToken = " + userRefreshToken);
        int cookieMaxAge = (int) refreshTokenExpiry / 60;

        CookieUtil.deleteCookie(request, response, REFRESH_TOKEN);
        CookieUtil.addCookie(response, REFRESH_TOKEN, refreshToken.getToken(), cookieMaxAge);

        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("token", accessToken.getToken())
                .build().toUriString();
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        authorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    private boolean hasAuthority(Collection<? extends GrantedAuthority> authorities, String authority) {
        if (authorities == null) {
            return false;
        }

        for (GrantedAuthority grantedAuthority : authorities) {
            if (authority.equals(grantedAuthority.getAuthority())) {
                return true;
            }
        }
        return false;
    }

    private boolean isAuthorizedRedirectUri(String uri) {
        System.out.println("uri = " + uri);
        URI clientRedirectUri = URI.create(uri);
        System.out.println(clientRedirectUri);
        return appProperties.getOauth2().getAuthorizedRedirectUris()
                .stream()
                .anyMatch(authorizedRedirectUri -> {
                    // Only validate host and port. Let the clients use different paths if they want to
                    URI authorizedURI = URI.create(authorizedRedirectUri);
                    System.out.println("authorizedURI = " + authorizedURI);
                    if(authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
                            && authorizedURI.getPort() == clientRedirectUri.getPort()) {
                        return true;
                    }
                    return false;
                });
    }
}

package com.d209.mungtopia.config.security;

import com.d209.mungtopia.api.repository.user.UserRefreshTokenRepository;
import com.d209.mungtopia.config.properties.AppProperties;
import com.d209.mungtopia.config.properties.CorsProperties;
import com.d209.mungtopia.oauth.entity.RoleType;
import com.d209.mungtopia.oauth.exception.RestAuthenticationEntryPoint;
import com.d209.mungtopia.oauth.filter.TokenAuthenticationFilter;
import com.d209.mungtopia.oauth.handler.OAuth2AuthenticationFailureHandler;
import com.d209.mungtopia.oauth.handler.OAuth2AuthenticationSuccessHandler;
import com.d209.mungtopia.oauth.handler.TokenAccessDeniedHandler;
import com.d209.mungtopia.oauth.repository.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.d209.mungtopia.oauth.service.CustomOAuth2UserService;
import com.d209.mungtopia.oauth.service.CustomUserDetailsService;
import com.d209.mungtopia.oauth.token.AuthTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
// WebSecurityConfigurerAdapter -> deprecated 됨
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsProperties corsProperties;
    private final AppProperties appProperties;
    private final AuthTokenProvider tokenProvider;
    private final CustomUserDetailsService userDetailsService;
    private final CustomOAuth2UserService oAuth2UserService;
    private final TokenAccessDeniedHandler tokenAccessDeniedHandler;
    private final UserRefreshTokenRepository userRefreshTokenRepository;

    /*
    * UserDetailsService 설정
    * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .cors()
                .and()
                    .sessionManagement()
                // 웹 통신 방식 -> stateless 설정 - jwt 때문에 가능
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // jwt 토큰 방식으로 인증 및 인가 처리를 하기 때문에 Security에서
                // 기본적으로 제공하는 formLogin 옵션 비활성화
                // 자체적으로 Custom
                    .csrf().disable()
                    .formLogin().disable()
                    .httpBasic().disable()
                    .exceptionHandling()
                // 인증 처리 과정에서 예외가 발생한 경우 예외를 핸들링
                    .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                    .accessDeniedHandler(tokenAccessDeniedHandler)
                .and()
                // 이 요청과 맞는 것들은 어떠한 요청없이 허용
                    .authorizeRequests()
                // Swagger 관련 보안 설정
                    .antMatchers(
                            "/v2/api-docs", "/swagger-resources/**",
                            "/swagger-ui/index.html", "/swagger-ui.html",
                            "/webjars/**", "/swagger/**", "/test/**").permitAll()
                    .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                // 패턴과 맞을 때 특정 권한을 갖는 사용자만 접근 가능
                    .antMatchers("/api/**").hasAnyAuthority(RoleType.USER.getCode())
                    .antMatchers("/api/**/admin/**").hasAnyAuthority(RoleType.ADMIN.getCode())
                // 그 외 모든 요청은 인증을 받음
                    .anyRequest().authenticated()
                .and()
                    .oauth2Login() // OAuth2 기반의 로그인인 경우
                // baseUri와 이어지는 내용
                // 각 OAuth 클ㄹ라이언트 링크와 baseuri가 일치해야함
                //  <a href="/login/oauth2/authorization/google">Google</a> 이렇게
                    .authorizationEndpoint()
                    .baseUri("/oauth2/authorization")
                // 인가 요청을 시작한 시점부터 인가 요청을 받는 시점까지 OAuth2AuthorizationRequest를 유지해준다.
                    .authorizationRequestRepository(oAuth2AuthorizationRequestBasedOnCookieRepository())
                .and()
                // 서버가 리소스 소유자의 user-agent를 통해 가져온 인가 응답을 클라이언트에게 전송할 때 사용
                // 디폴트 인가 응답 baseuri는 "**/login/oauth2/code/***" 이다
                    .redirectionEndpoint()
                    .baseUri("/*/oauth2/code/*")
                .and()
                // 로그인 성공 후 사용자 정보를 가져온다,
                    .userInfoEndpoint()
                // 사용자 정보를 처리할 때 사용
                    .userService(oAuth2UserService)
                .and()
                    .successHandler(oAuth2AuthenticationSuccessHandler())
                    .failureHandler(oAuth2AuthenticationFailureHandler());
        // 지정된 필터 앞에 커스텀 필터를 추가
        // 인증을 처리하는 기본필터 UsernamePasswordAuthenticationFilter 전에 필터를 실행하겠다.
        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    /*
    * auth 매니저 설정
    * */
    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /*
    * security 설정 시, 사용할 인코더 설정
    * BCryptPasswordEncoder는 Spring Security에서 제공하는 비밀번호 암호화 객체입니다.
    * Service에서 비밀번호를 암호화할 수 있도록 Bean으로 등록합니다.
    * */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
    * 토큰 필터 설정
    * */
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(tokenProvider);
    }

    /*
    * 쿠키 기반 인가 Repository
    * 인가 응답을 연계 하고 검증할 때 사용.
    * */
    @Bean
    public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository() {
        return new OAuth2AuthorizationRequestBasedOnCookieRepository();
    }

    /*
    * Oauth 인증 성공 핸들러
    * */
    @Bean
    public OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
        return new OAuth2AuthenticationSuccessHandler(
                tokenProvider,
                appProperties,
                userRefreshTokenRepository,
                oAuth2AuthorizationRequestBasedOnCookieRepository()
        );
    }

    /*
     * Oauth 인증 실패 핸들러
     * */
    @Bean
    public OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler() {
        return new OAuth2AuthenticationFailureHandler(oAuth2AuthorizationRequestBasedOnCookieRepository());
    }

    /*
    * Cors 설정
    * Cors는 반드시 Spring Security에 앞서 처리되어야함
    * - preflight request는 JSESSIONID같은 쿠키를 포함하고 있지 않고,
    * 이는 Request가 인증되지(not authenticated)않은 사용자라고 판단하고 거절
    *
    * - CorsFilter을 이용해서 CORS해결
    * CorsFilter는 CORS를 해결할 수 있다.
    * CorsConfigurationSource를 제공해 CorsFilter를 Spring Security에 통합가능
    * */
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource corsConfigSource = new UrlBasedCorsConfigurationSource();

        // Cors 요청이 어떻게 허용 origin, 헤더, 메소드 등처리 되어야하는 방법을 지정
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedHeaders(Arrays.asList(corsProperties.getAllowedHeaders().split(",")));
        corsConfig.setAllowedMethods(Arrays.asList(corsProperties.getAllowedMethods().split(",")));
        corsConfig.setAllowedOrigins(Arrays.asList(corsProperties.getAllowedOrigins().split(",")));
        corsConfig.setAllowCredentials(true);
        corsConfig.setMaxAge(corsConfig.getMaxAge());

        corsConfigSource.registerCorsConfiguration("/**", corsConfig);
        return corsConfigSource;
    }
}

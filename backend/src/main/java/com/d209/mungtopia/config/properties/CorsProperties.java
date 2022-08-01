package com.d209.mungtopia.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
// properties 파일의 key값이 아래와 같이 같은 값으로 시작할 때,
// 그것을 묶어서 Bean으로 등록 가능
// cors.allow-origins 나 cors.allowOrigins에 값을 바인딩 해준다.
// Application 클래스에 빈등록 필요!
@ConfigurationProperties(prefix = "cors")
public class CorsProperties {
    private String allowedOrigins;
    private String allowedMethods;
    private String allowedHeaders;
    private Long maxAge;
}

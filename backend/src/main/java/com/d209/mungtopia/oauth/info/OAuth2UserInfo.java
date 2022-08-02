package com.d209.mungtopia.oauth.info;

import java.util.Map;
// 모든 OAuth2 공급자는 인증된 사용자의 세부 정보를 가져올 때 다른 JSON 응답을 반화 ㄴ
// Spring Security 는 Map 형식으로 응답 구문을 분석
public abstract class OAuth2UserInfo {
    protected Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public abstract String getId();

    public abstract String getName();

    public abstract String getEmail();

    public abstract String getImageUrl();
}

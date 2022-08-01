package com.d209.mungtopia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// Swagger 주소
// http://localhost:8080/swagger-ui.html

@Configuration
@EnableSwagger2 //swagger에 해당하는 어노테이션을 작성한다.
@EnableWebMvc //이것도 함께 작성
public class SwaggerConfig implements WebMvcConfigurer {

    //swagger 2.9.2 버전 리소스 등록
    //spring-security와 연결할 때 이 부분을 작성하지 않으면 404에러가 뜬다.
    // 리소스를 등록하는 코드 작성
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket api() { //swagger를 연결하기 위한 Bean 작성
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //  Swagger API 문서로 만들기 원하는 BasePackage 경로 - 필수
                // RequestHandlerSelectors.any() - 현재 RequestMapping으로 할당된 모든 URL 리스트 추출
                .apis(RequestHandlerSelectors.any())
                // URL 경로를 지정하여 해당 URL에 해당하는 요청만 Swagger문서로 만든다 - 필수
                .paths(PathSelectors.any())
                .build()
                // Swagger API 문서에 대한 설명을 표기하는 메소드 - 선택
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() { //선택
        return new ApiInfoBuilder()
                .title("Mungtopia REST API") //자신에게 맞는 타이틀을 작성해준다.
                .description("mungtopi backend api document") //알맞는 description을 작성해준다.
                .version("1.0") //알맞는 버전을 작성해준다.
                .build();
    }
}

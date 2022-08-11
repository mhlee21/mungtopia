package com.d209.mungtopia;

import com.d209.mungtopia.config.properties.AppProperties;
import com.d209.mungtopia.config.properties.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
// 해당 클래스를 빈으로 등록하고, 프로퍼티 값을 할당
// -> 이렇게 Configuration을 이용해서 지정할 수도 있고 각각의 클래스에 @Configuration을 붙여도 된다.
@EnableConfigurationProperties({
		AppProperties.class
//		CorsProperties.class
})
@CrossOrigin("http://localhost:3000")
public class MungtopiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MungtopiaApplication.class, args);
	}

}

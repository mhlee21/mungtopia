package com.d209.mungtopia;

import com.d209.mungtopia.config.properties.AppProperties;
import com.d209.mungtopia.config.properties.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		CorsProperties.class,
		AppProperties.class
})
public class MungtopiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MungtopiaApplication.class, args);
	}

}

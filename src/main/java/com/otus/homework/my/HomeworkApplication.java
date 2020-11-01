package com.otus.homework.my;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HomeworkApplication {
    public static void main(String[] args) {
        SpringApplication.run(HomeworkApplication.class, args);
    }

//    @Bean
//    public OpenAPI customOpenAPI(@Value("") String appDesciption, @Value("") String appVersion) {
//        return new OpenAPI()
//                .info(new Info()
//                    .title("Homework02")
//                    .version(appVersion)
//                    .description(appDesciption)
//                    .termsOfService("http://swagger.io/terms/")
//                    .license(new License().name("Apache 2.0").url("http://springdoc.org")
//                    )
//                );
//    }

}

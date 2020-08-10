package com.otus.homework02.my;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/*
https://www.youtube.com/watch?v=HHyjWc0ASl8&list=PLHUh_U_-DYtT-r046W1-iqHNn6MO6SJW3&index=2&t=0s
https://www.programcreek.com/java-api-examples/?api=springfox.documentation.service.ApiInfo

https://dzone.com/articles/openapi-3-documentation-with-spring-boot
https://github.com/teq-niq/sample/blob/springdoc-openapi-intro/pom.xml

https://www.youtube.com/playlist?list=PLWieu6NbbqTwwYwylgXmmKVX1ZWsUVx8m
https://github.com/pairlearning/expense-tracker-api
*/

@SpringBootApplication
public class Homework02Application {
    public static void main(String[] args) {
        SpringApplication.run(Homework02Application.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI(@Value("${application-description}") String appDesciption, @Value("${application-version}") String appVersion) {
        return new OpenAPI()
                .info(new Info()
                    .title("Homework02 API")
                    .version(appVersion)
                    .description(appDesciption)
                    .termsOfService("http://swagger.io/terms/")
                    .license(new License().name("Apache 2.0").url("http://springdoc.org")
                    )
                );
    }

}

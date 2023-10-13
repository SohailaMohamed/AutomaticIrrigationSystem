package com.automaicirrigationsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Configuration
@EnableSwagger2
public class AutomaticIrrigationSystem {
    public static void main(String[] args) {
        SpringApplication.run(AutomaticIrrigationSystem.class, args);
    }

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Automatic Irrigation System API")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Automatic Irrigation System API")
                .description("Endpoints for Automatic Irrigation operations.")
                .license("Created by Sohaila Esawy.")
                .licenseUrl("soohaila.mohamed12@gmail.com")
                .version("1.0").build();
    }
}
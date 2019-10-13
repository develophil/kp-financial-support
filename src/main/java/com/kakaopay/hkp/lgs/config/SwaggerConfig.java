package com.kakaopay.hkp.lgs.config;

import com.kakaopay.hkp.lgs.security.jwt.JWTFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {

        List<Parameter> global = Collections.singletonList(new ParameterBuilder()
                .name("Authorization")
                .description("JWT Token")
                .parameterType("header")
                .required(false)
                .defaultValue(JWTFilter.TOKEN_BEARER)
                .modelRef(new ModelRef("string"))
                .build());

        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(global)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kakaopay.hkp.lgs"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("지자체협약지원 API")
                .description("kakaopay 서버개발 사전과제1 - 지자체협약지원 API")
                .build();
    }
}

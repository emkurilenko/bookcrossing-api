package com.bookcrossing.api.config;

import static java.util.Collections.singletonList;

import io.swagger.models.auth.In;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RestController;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securitySchemes(basicScheme())
                .securityContexts(securityContext())
                .select()
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }

    private List<SecurityContext> securityContext() {
        return List.of(SecurityContext.builder()
                .securityReferences(basicAuthReference())
                .forPaths(PathSelectors.any())
                .build());
    }

    private List<SecurityScheme> basicScheme() {
        return List.of(new BasicAuth("basicAuth"));
    }

    private List<SecurityReference> basicAuthReference() {
        return List.of(new SecurityReference("basicAuth", new AuthorizationScope[0]));
    }

}

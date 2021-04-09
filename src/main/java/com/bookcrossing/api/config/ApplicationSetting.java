package com.bookcrossing.api.config;

import lombok.Getter;

import org.springframework.stereotype.Component;

@Getter
@Component
public class ApplicationSetting {

    private final Long limitReservation = 60L; //in minutes //TODO move to application.yml

    private final String[] whiteList = {"/",
            "/error",
            "/favicon.ico",
            "/**/*.png",
            "/**/*.gif",
            "/**/*.svg",
            "/**/*.jpg",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js",
            "/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/swagger-ui/",
            "/webjars/**",
            "/user/register",
            "/file/**",
            "/file",
            "/**/search",
            "/location/**/books",
            "/bug-report"
    };
}

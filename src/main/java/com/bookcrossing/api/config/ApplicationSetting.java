package com.bookcrossing.api.config;

import lombok.Getter;

import org.springframework.stereotype.Component;

@Getter
@Component
public class ApplicationSetting {

    private final Long limitReservation = 60L; //in minutes //TODO move to application.yml
}

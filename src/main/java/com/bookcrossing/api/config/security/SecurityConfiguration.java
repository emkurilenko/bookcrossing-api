package com.bookcrossing.api.config.security;

import com.bookcrossing.api.config.ApplicationSetting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final ApplicationSetting applicationSetting;
    private final DbAuthenticationProvider dbAuthenticationProvider;

    @Autowired
    public SecurityConfiguration(
            ApplicationSetting applicationSetting,
            DbAuthenticationProvider dbAuthenticationProvider) {
        this.applicationSetting = applicationSetting;
        this.dbAuthenticationProvider = dbAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(applicationSetting.getWhiteList())
                .permitAll()
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().sessionManagement().disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder) {
        builder.authenticationProvider(dbAuthenticationProvider);
    }

}

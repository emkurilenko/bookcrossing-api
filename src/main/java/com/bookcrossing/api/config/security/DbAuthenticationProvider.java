package com.bookcrossing.api.config.security;

import com.bookcrossing.api.domain.dto.UserDTO;
import com.bookcrossing.api.service.impl.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public final class DbAuthenticationProvider implements AuthenticationProvider {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DbAuthenticationProvider(
            final UserServiceImpl userService,
            final PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        final UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        UserDTO userDTO = userService.findByLogin(token.getName());

        if (userDTO == null) {
            return null;
        }

        if (passwordEncoder.matches((String) token.getCredentials(), userDTO.getPassword())) {

            final BookCrossingUserDetails bookCrossingUserDetails = new BookCrossingUserDetails(
                    userDTO);

            final UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            token.getPrincipal(),
                            token.getCredentials(),
                            bookCrossingUserDetails.getAuthorities());
            auth.setDetails(bookCrossingUserDetails);
            return auth;
        }

        return null;
    }
}

package com.bookcrossing.api.utils;

import static java.util.Optional.ofNullable;

import com.bookcrossing.api.config.security.BookCrossingUserDetails;
import com.bookcrossing.api.domain.dto.user.UserDTO;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtils {

    public BookCrossingUserDetails getUserDetailsFromSecurityContextHolder() {
        final Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (authentication != null
                && authentication.getDetails() instanceof BookCrossingUserDetails) {
            return (BookCrossingUserDetails) authentication.getDetails();
        }

        if (authentication != null
                && authentication.getPrincipal() instanceof BookCrossingUserDetails) {
            return (BookCrossingUserDetails) authentication.getPrincipal();
        }

        return null;
    }

    public UserDTO getCurrentUser() {
        return ofNullable(getUserDetailsFromSecurityContextHolder())
                .map(BookCrossingUserDetails::getUser)
                .orElseThrow(() -> new IllegalStateException("user.does.contains.in.context"));
    }
}

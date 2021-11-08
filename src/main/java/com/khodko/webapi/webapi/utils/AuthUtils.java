package com.khodko.webapi.webapi.utils;

import com.khodko.webapi.webapi.dto.ProfileDto;
import com.khodko.webapi.webapi.securiry.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class AuthUtils {

    public CustomUserDetails getUserDetailsFromSecurityContextHolder() {
        final Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (authentication != null
                && authentication.getDetails() instanceof CustomUserDetails) {
            return (CustomUserDetails) authentication.getDetails();
        }

        if (authentication != null
                && authentication.getPrincipal() instanceof CustomUserDetails) {
            return (CustomUserDetails) authentication.getPrincipal();
        }

        return null;
    }

    public ProfileDto getCurrentUser() {
        return ofNullable(getUserDetailsFromSecurityContextHolder())
                .map(CustomUserDetails::getProfile)
                .orElseThrow(() -> new IllegalStateException("user.does.contains.in.context"));
    }
}

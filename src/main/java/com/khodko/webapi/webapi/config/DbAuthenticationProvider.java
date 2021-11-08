package com.khodko.webapi.webapi.config;

import com.khodko.webapi.webapi.dto.ProfileDto;
import com.khodko.webapi.webapi.securiry.CustomUserDetails;
import com.khodko.webapi.webapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public final class DbAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DbAuthenticationProvider(
            final UserService userService,
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
        ProfileDto profileDTO = userService.findByLogin(token.getName());

        if (profileDTO == null) {
            return null;
        }

        if (passwordEncoder.matches((String) token.getCredentials(), profileDTO.getPass())) {

            final CustomUserDetails bookCrossingUserDetails = new CustomUserDetails(profileDTO);

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

package org.wora.majesticcup.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.wora.majesticcup.config.JwtTokenUtil;
import org.wora.majesticcup.dto.auth.LoginRequestDTO;

@Service
@Slf4j
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthService(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public String login(LoginRequestDTO loginDTO) {
        log.debug("Attempting authentication for user: {}", loginDTO.username());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.username(),
                            loginDTO.password()
                    )
            );

            return jwtTokenUtil.generateToken(authentication);
        } catch (BadCredentialsException e) {
            log.error("Authentication failed for user: {}", loginDTO.username());
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}

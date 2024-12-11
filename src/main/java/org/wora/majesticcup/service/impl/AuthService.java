package org.wora.majesticcup.service.impl;


import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.wora.majesticcup.config.JwtTokenUtil;
import org.wora.majesticcup.dto.auth.LoginRequestDTO;
import org.wora.majesticcup.entity.AppUser;
import org.wora.majesticcup.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;


    public String login(LoginRequestDTO loginDTO){
        AppUser user = userRepository.findByUsername(loginDTO.username())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));


        if (!passwordEncoder.matches(loginDTO.password() , user.getPassword())){
            throw new RuntimeException("invalid Password");
        }

        return jwtTokenUtil.generateToken(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        null,
                        List.of(new SimpleGrantedAuthority(user.getRole().name()))
                )
        );
    }

}

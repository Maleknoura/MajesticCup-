package org.wora.majesticcup.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.wora.majesticcup.entity.AppUser;
import org.wora.majesticcup.repository.UserRepository;

@Service

public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    public UserDetailService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur not found"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(appUser.getUsername())
                .password(appUser.getPassword())
                .authorities(new SimpleGrantedAuthority("ROLE_" + appUser.getRole().name()))
                .build();
    }
}
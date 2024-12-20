package org.wora.majesticcup.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.wora.majesticcup.dto.user.ResponseUserDto;
import org.wora.majesticcup.dto.user.UserRequestDto;
import org.wora.majesticcup.entity.AppUser;
import org.wora.majesticcup.entity.Role;
import org.wora.majesticcup.mapper.UserMapper;
import org.wora.majesticcup.repository.UserRepository;
import org.wora.majesticcup.service.interfaces.UserService;

import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper=userMapper;
    }

    public AppUser register(UserRequestDto registerDTO) {
        if (userRepository.findByUsername(registerDTO.username()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        AppUser newUser = new AppUser();
        newUser.setUsername(registerDTO.username());
        newUser.setPassword(passwordEncoder.encode(registerDTO.password()));
        newUser.setRole(registerDTO.role());

        log.debug("Creating new user with username: {}", registerDTO.username());
        return userRepository.save(newUser);
    }

    @Override
    public Page<ResponseUserDto> findAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toResponse);
    }

    @Override
    public ResponseUserDto findById(String id) {
        AppUser user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));

        return userMapper.toResponse(user);
    }

    @Override
    public boolean delete(String id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }

        userRepository.deleteById(id);
        log.debug("Deleted user with ID: {}", id);
        return true;
    }
}

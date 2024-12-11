package org.wora.majesticcup.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.wora.majesticcup.dto.user.ResponseUserDto;
import org.wora.majesticcup.dto.user.UserRequestDto;
import org.wora.majesticcup.entity.AppUser;
import org.wora.majesticcup.mapper.UserMapper;
import org.wora.majesticcup.repository.UserRepository;
import org.wora.majesticcup.service.interfaces.UserService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void register(UserRequestDto userRequestDto) {
        Optional<AppUser> existingUser = userRepository.findByUsername(userRequestDto.username());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("User already exists with the username: " + userRequestDto.username());
        }
        AppUser user = userMapper.toEntity(userRequestDto);
        userRepository.save(user);
    }

    @Override
    public Page<ResponseUserDto> findAll(Pageable pageable) {
        Page<AppUser> users = userRepository.findAll(pageable);
        if (users.isEmpty()) {
            throw new RuntimeException("No users found");
        }
        return users.map(userMapper::toResponse);
    }

    @Override
    public ResponseUserDto findById(String id) {
        AppUser user = userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("User with the id " + id + " does not exist"));
        return userMapper.toResponse(user);
    }

    @Override
    public boolean delete(String id) {
        Optional<AppUser> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            userRepository.deleteById(id);
            return true;
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }



}

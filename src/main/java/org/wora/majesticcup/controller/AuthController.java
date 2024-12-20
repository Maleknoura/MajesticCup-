package org.wora.majesticcup.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wora.majesticcup.dto.auth.LoginRequestDTO;
import org.wora.majesticcup.dto.user.UserRequestDto;
import org.wora.majesticcup.service.impl.AuthService;
import org.wora.majesticcup.service.interfaces.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequestDto userRequestDto) {
        userService.register(userRequestDto);
        return ResponseEntity.ok("Registered Successfully");
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequestDTO loginDTO){
        String token = authService.login(loginDTO);
        return ResponseEntity.ok(token);
    }
}

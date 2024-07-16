package com.teamchallege.authservice.controller;

import com.teamchallege.authservice.dto.CreateUserDto;
import com.teamchallege.authservice.dto.JwtResponseDto;
import com.teamchallege.authservice.dto.LoginUserDto;
import com.teamchallege.authservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/open/auth")
@Validated
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(@Lazy AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("Test");
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody @Valid LoginUserDto loginUserDto) {
        return ResponseEntity.ok(authService.login(loginUserDto));
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponseDto> register(@RequestBody @Valid CreateUserDto createUserDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(createUserDto));
    }
}

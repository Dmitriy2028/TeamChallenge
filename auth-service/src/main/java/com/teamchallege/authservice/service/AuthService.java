package com.teamchallege.authservice.service;

import com.teamchallege.authservice.dto.CreateUserDto;
import com.teamchallege.authservice.dto.JwtResponseDto;
import com.teamchallege.authservice.dto.LoginUserDto;
import com.teamchallege.authservice.jwt.JwtUtils;
import com.teamchallege.common.entities.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RestTemplate restTemplate;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;


    public JwtResponseDto login(LoginUserDto loginUserDto) {
        User loggedUser = restTemplate.postForObject("http://user-service/internal/user/getUserByEmail", loginUserDto.getEmail(), User.class);

        if (loggedUser == null || !passwordEncoder.matches(loginUserDto.getPassword(), loggedUser.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        String jwt = jwtUtils.generateToken(loggedUser);
        return new JwtResponseDto(jwt);
    }

    public JwtResponseDto register(CreateUserDto createUserDto) {
        User registeredUser = restTemplate.postForObject("http://user-service/internal/user/createUser", createUserDto, User.class);
        String jwt = jwtUtils.generateToken(registeredUser);
        return new JwtResponseDto(jwt);
    }
}

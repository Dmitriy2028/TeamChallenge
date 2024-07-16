package com.teamchallege.userservice.user.controller;

import com.teamchallege.userservice.user.dto.ResponseUserDto;
import com.teamchallege.userservice.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.teamchallege.userservice.user.service.UserService;

@RestController
@RequestMapping("api/open/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userMapper.mapToResponseUserDto(userService.getUserById(id)));
    }

    @GetMapping("/{email}")
    public ResponseEntity<ResponseUserDto> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userMapper.mapToResponseUserDto(userService.findByEmail(email)));
    }

}

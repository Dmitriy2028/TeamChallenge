package com.teamchallege.userservice.user.controller;

import com.teamchallege.common.entities.cart.CartItem;
import com.teamchallege.common.entities.user.User;
import com.teamchallege.userservice.user.dto.CreateUserDto;
import com.teamchallege.userservice.user.dto.ResponseUserDto;
import com.teamchallege.userservice.user.mapper.UserMapper;
import com.teamchallege.userservice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("internal/user")
@RequiredArgsConstructor
public class InternalUserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/getUserById")
    public ResponseEntity<User> getUserById(@RequestBody Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/getUserByEmail")
    public ResponseEntity<User> getUserByEmail(@RequestBody String email) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto){
        return ResponseEntity.ok(userService.createUser(createUserDto));
    }
}

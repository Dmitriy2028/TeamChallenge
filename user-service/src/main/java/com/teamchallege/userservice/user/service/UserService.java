package com.teamchallege.userservice.user.service;


import com.teamchallege.common.entities.user.User;
import com.teamchallege.userservice.user.dto.CreateUserDto;


public interface UserService {

    User createUser(CreateUserDto createUserDto);

    boolean existsByEmail(String email);

    User findByEmail(String email);

    void save(User user);

    User getUserById(Long id);
}

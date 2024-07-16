package com.teamchallege.userservice.user.mapper;

import com.teamchallege.common.entities.user.User;
import com.teamchallege.userservice.user.dto.ResponseUserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserMapper {

    public ResponseUserDto mapToResponseUserDto(User user) {
        return ResponseUserDto
                .builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}

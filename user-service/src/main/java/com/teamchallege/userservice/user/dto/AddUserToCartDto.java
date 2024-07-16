package com.teamchallege.userservice.user.dto;

import com.teamchallege.common.entities.user.User;
import lombok.Data;

@Data
public class AddUserToCartDto {
    private User user;
    private Long cartHeaderId;
}

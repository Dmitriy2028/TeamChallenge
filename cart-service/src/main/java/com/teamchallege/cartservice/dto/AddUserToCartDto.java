package com.teamchallege.cartservice.dto;

import com.teamchallege.common.entities.user.User;
import lombok.Data;

@Data
public class AddUserToCartDto {
    private User user;
    private Long cartHeaderId;
}

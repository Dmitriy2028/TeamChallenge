package com.teamchallege.userservice.user.dto;


import com.teamchallege.userservice.user.annotation.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserDto {
    @NotBlank
    @Email
    @UniqueEmail(message = "Email already exists")
    private String email;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Size(min = 8)
    private String password;

    private Long cartHeaderId;
}

package com.insup.user.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UserRequest {

    @NotNull(message = "userId not good")
    private String userId;

    @NotNull(message = "email not good")
    @Size(min = 2, message = "Email not be less than two characters")
    @Email
    private String email;

    @NotNull(message = "Name not good")
    @Size(min = 2, message = "Name not be less than two characters")
    private String name;

    @NotNull(message = "password not good")
    @Size(min = 2, message = "password not be less than two characters")
    private String password;
}

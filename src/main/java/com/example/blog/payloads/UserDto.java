package com.example.blog.payloads;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;

    @NotEmpty
    @Size(min = 4, max = 50, message = "Username must be 4 characters")
    private String name;

    @NotEmpty
    @Email(message = "Email address is not valid !!")
    private String email;

    @NotEmpty
    @Size(min = 3)
    private String password;

    @NotEmpty
    private String about;
}

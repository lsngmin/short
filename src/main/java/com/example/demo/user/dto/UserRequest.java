package com.example.demo.user.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequest {

    @Email(message = "BAD REQUEST")
    @NotNull(message = "BAD REQUEST")
    @NotBlank(message = "BAD REQUEST")
    @Size(max = 254, message = "BAD REQUEST")
    private String email;

    @Size(min = 8, max = 20, message = "BAD REQUEST")
    @NotBlank(message = "BAD REQUEST")
    @NotNull(message = "BAD REQUEST")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-\\[\\]{}|;:'\",.<>?/]).{8,20}$", message = "BAD REQUEST")
    private String pw;
}

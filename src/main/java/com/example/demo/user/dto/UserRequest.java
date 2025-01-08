package com.example.demo.user.dto;

import com.example.demo.user.entity.UserEntity;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
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

    public Map<String, Object> getDataMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        return map;
    }
    public UserRequest(UserEntity userEntity){
        this.email = userEntity.getEmail();
        this.pw = userEntity.getPassword();
    }
}

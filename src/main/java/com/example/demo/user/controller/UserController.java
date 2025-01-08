package com.example.demo.user.controller;

import com.example.demo.user.dto.UserRequest;
import com.example.demo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Validated UserRequest request) {
        String email = request.getEmail();
        userService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest request) {
        return userService.login(request) ? ResponseEntity.status(HttpStatus.OK).build() : ResponseEntity.badRequest().build();
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping ("/list")
    public Map<String, String> list() {
        return Map.of("dawdwa", "awdawdawd");
    }
}

package com.example.demo.user.controller;

import com.example.demo.security.util.JWTUtil;
import com.example.demo.user.dto.UserRequest;
import com.example.demo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/token")
@Log4j2
@RequiredArgsConstructor
public class TokenController {
    private final UserService userService;
    private final JWTUtil jwtUtil;

    @PostMapping("/make")
    public ResponseEntity<Map<String, String>> makeToken(@RequestBody UserRequest request) {
        UserRequest user = userService.login_JWT(request);
        String accessToken = jwtUtil.createToken(user.getDataMap(), 10);
        String refreshToken = jwtUtil.createToken(Map.of("email", user.getEmail()), 10);
        return ResponseEntity.ok(Map.of("access_token", accessToken, "refresh_token", refreshToken));
    }

}

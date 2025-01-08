package com.example.demo.user.service;

import com.example.demo.user.dto.UserRequest;
import com.example.demo.user.entity.UserEntity;
import com.example.demo.user.exception.UserExceptions;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String signup(UserRequest request) {
        String email = request.getEmail();
        if (isBlocked(email))
            throw UserExceptions.FORBIDDEN.get();
        if(userRepository.existsByEmail(request.getEmail()))
            throw UserExceptions.DUPLICATE.get();
        return userRepository.save(UserEntity.builder()
                        .email(request.getEmail())
                        .role("USER")
                        .pw(bCryptPasswordEncoder.encode(request.getPw()))
                .build()).getEmail();
    }

    public boolean login(UserRequest request) {
        String email = request.getEmail();
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> UserExceptions.NOT_FOUND.get());

        return bCryptPasswordEncoder.matches(request.getPw(), user.getPw());
    }
    public UserRequest login_JWT(UserRequest request) {
        String email = request.getEmail();
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> UserExceptions.NOT_FOUND.get());
        if(!bCryptPasswordEncoder.matches(request.getPw(), user.getPw())) {
            throw UserExceptions.BAD_REQUEST.get();
        }
        return new UserRequest(user);
    }

    private static final int MAX_ATTEMPTS = 5;
    private static final long LOCK_TIME_DURATION = 30 * 60 * 1000; // 30 minutes
    private final Map<String, Integer> attemptsCache = new HashMap<>();
    private final Map<String, Long> lockTimeCache = new HashMap<>();

    public boolean isBlocked(String email) {
        if (lockTimeCache.containsKey(email)) {
            long lockTime = lockTimeCache.get(email);
            if (System.currentTimeMillis() - lockTime < LOCK_TIME_DURATION) {
                return true; // Blocked
            }
            // Unblock after lock time passed
            lockTimeCache.remove(email);
        }
        return false;
    }

    public void signupFailed(String email) {
        attemptsCache.put(email, attemptsCache.getOrDefault(email, 0) + 1);
        if (attemptsCache.get(email) >= MAX_ATTEMPTS) {
            lockTimeCache.put(email, System.currentTimeMillis());
        }
    }

    public void signupSuccessful(String email) {
        attemptsCache.remove(email);
    }
}

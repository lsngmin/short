package com.example.demo.user.aspect;

import com.example.demo.user.dto.UserRequest;
import com.example.demo.user.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserAspect {
    private final UserService userService;

    public UserAspect(UserService userService) {
        this.userService = userService;
    }
    @AfterThrowing(value = "execution(* com.example.demo.user.service.UserService.signup(..))", throwing = "exception")
    public void handleSignupFailure(JoinPoint joinPoint, Exception exception) {
        UserRequest request = (UserRequest) joinPoint.getArgs()[0];
        String email = request.getEmail();
        if (!userService.isBlocked(email)) {
            userService.signupFailed(email);
        }
    }

    @AfterReturning(value = "execution(* com.example.demo.user.service.UserService.signup(..))", returning = "result")
    public void handleSignupSuccess(JoinPoint joinPoint, Object result) {
        UserRequest request = (UserRequest) joinPoint.getArgs()[0];
        String email = request.getEmail();
        userService.signupSuccessful(email);
    }
}

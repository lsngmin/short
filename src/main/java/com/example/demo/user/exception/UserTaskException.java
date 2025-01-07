package com.example.demo.user.exception;

import lombok.Getter;

@Getter
public class UserTaskException extends RuntimeException {
    private String msg;
    private int code;

    public UserTaskException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }
}

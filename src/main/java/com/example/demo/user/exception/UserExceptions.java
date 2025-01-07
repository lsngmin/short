package com.example.demo.user.exception;

public enum UserExceptions {
    NOT_FOUND("NOT FOUND", 404),
    DUPLICATE("DUPLICATE", 409),
    INVALID("INVALID", 400),
    BAD_REQUEST("BAD REQUEST", 400),
    FORBIDDEN("FORBIDDEN", 403);

    private UserTaskException userTaskException;
    UserExceptions(String msg, int code) {

        this.userTaskException = new UserTaskException(msg, code);
    }
    public UserTaskException get() {
        return userTaskException;
    }
}

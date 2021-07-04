package com.kayafirat.blog.exception.custom;

import java.time.LocalDateTime;

public class UserEmailAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserEmailAlreadyExistsException(String email) {
        super("This email already taken :"+email);
    }

    @Override
    public String getLocalizedMessage() {
        return super.getLocalizedMessage();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public LocalDateTime getTime() {
        return LocalDateTime.now();
    }

}

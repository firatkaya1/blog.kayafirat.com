package com.kayafirat.blog.exception.custom;

import java.time.LocalDateTime;

public class UserEmailNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserEmailNotFoundException(String email) {
        super("There is no user in this email:"+email);
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

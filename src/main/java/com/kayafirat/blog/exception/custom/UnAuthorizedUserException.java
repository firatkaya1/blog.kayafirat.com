package com.kayafirat.blog.exception.custom;

import java.time.LocalDateTime;

public class UnAuthorizedUserException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnAuthorizedUserException() {
        super("Unauthorized request:");
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
package com.kayafirat.blog.exception.custom;

import java.time.LocalDateTime;

public class SamePasswordException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SamePasswordException() {
        super("New and Old passwords can not be same !");
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

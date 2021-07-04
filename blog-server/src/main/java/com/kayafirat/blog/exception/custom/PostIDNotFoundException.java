package com.kayafirat.blog.exception.custom;

import java.time.LocalDateTime;

public class PostIDNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PostIDNotFoundException(Long id) {
        super("There is no post in this id:"+id);
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

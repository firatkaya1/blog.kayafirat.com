package com.kayafirat.blog.exception.custom;

public class MailNotFoundException extends RuntimeException {

    public MailNotFoundException(Long id) {
        super("Not found mail in this id :" + id);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public String getLocalizedMessage() {
        return super.getLocalizedMessage();
    }

    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }
}

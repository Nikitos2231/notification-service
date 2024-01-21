package com.synthilearn.notificationservice.infra.exception;

public class TemplateException extends RuntimeException {

    private TemplateException(String message) {
        super(message);
    }

    public static TemplateException notFound(String message) {
        return new TemplateException(message);
    }
}

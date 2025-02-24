package com.mirkoabozzi.order_service.exceptions;

public class NotFoundExceptions extends RuntimeException {
    public NotFoundExceptions(String message) {
        super(message);
    }
}

package com.mirkoabozzi.product_service.exceptions;

public class NotFoundExceptions extends RuntimeException {
    public NotFoundExceptions(String message) {
        super(message);
    }
}

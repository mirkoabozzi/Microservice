package com.mirkoabozzi.order_service.exceptions;

import com.mirkoabozzi.order_service.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleAllErrors(Exception ex) {
        ex.printStackTrace();
        return new ErrorDTO("Server error", LocalDateTime.now());
    }

    @ExceptionHandler(NotFoundExceptions.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleNotFoundException(NotFoundExceptions ex) {
        return new ErrorDTO(ex.getMessage(), LocalDateTime.now());
    }
}

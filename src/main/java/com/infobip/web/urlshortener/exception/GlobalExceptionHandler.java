package com.infobip.web.urlshortener.exception;

import com.infobip.web.urlshortener.error.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(WebsiteNotFoundException.class)
    public ErrorInfo handleWebsiteNotFoundException(WebsiteNotFoundException ex, WebRequest request) {
        return new ErrorInfo(LocalDateTime.now(), ex.getMessage(), request.getDescription(false), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ErrorInfo handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        return new ErrorInfo(LocalDateTime.now(), ex.getMessage(), request.getDescription(false), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ErrorInfo handleMethodArgumentNotValid(Exception ex, WebRequest request) {
        return new ErrorInfo(LocalDateTime.now(), ex.getMessage(), request.getDescription(false), HttpStatus.BAD_REQUEST);
    }
}

package com.test.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchCityException.class)
    protected ResponseEntity<AwesomeException> handleNoSuchCityException() {
        return new ResponseEntity<>(new AwesomeException("There is no such city"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchMessageContentException.class)
    protected ResponseEntity<AwesomeException> handleNoSuchMessageContentException() {
        return new ResponseEntity<>(new AwesomeException("There is no such message content"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchMessageException.class)
    protected ResponseEntity<AwesomeException> handleNoSuchMessageException() {
        return new ResponseEntity<>(new AwesomeException("There is no such message"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ItemAlreadyExistException.class)
    protected ResponseEntity<AwesomeException> handleItemAlreadyExistException() {
        return new ResponseEntity<>(new AwesomeException("Item with such content already exist"), HttpStatus.FORBIDDEN);
    }

    @Data
    @AllArgsConstructor
    private static class AwesomeException {
        private String message;
    }
}
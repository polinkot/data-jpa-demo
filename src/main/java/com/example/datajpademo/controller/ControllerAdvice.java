package com.example.datajpademo.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class ControllerAdvice {
    private Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

    @Data
    @RequiredArgsConstructor
    private class ErrorInfo {
        private LocalDateTime timestamp = LocalDateTime.now();
        private final String exception;
        private final String error;
        private final String message;
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(DataAccessException.class)
    public ErrorInfo handleDataAccessException(DataAccessException exception) {
        logger.error("Data Access Exception", exception);
        return new ErrorInfo(exception.getClass().getName(), "Ошибка при получении/сохранении данных.", exception.getLocalizedMessage());
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorInfo handleException(Exception ex) {
        logger.error("Error!!!", ex);
        return new ErrorInfo(ex.getClass().getName(), "Ошибка сервера.", ex.getLocalizedMessage());
    }
}
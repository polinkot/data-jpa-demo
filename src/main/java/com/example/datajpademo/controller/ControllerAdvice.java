package com.example.datajpademo.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ControllerAdvice {
    private Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

    @Data
    @AllArgsConstructor
    public class ErrorInfo {
        private LocalDateTime dateTime;
        private String exception;
        private String message;
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(DataAccessException.class)
    public ErrorInfo handleDataAccessException(DataAccessException exception) {
        logger.error("Data Access Exception", exception);
        return new ErrorInfo(LocalDateTime.now(), exception.getClass().getName(), "Ошибка при получении/сохранении данных.");
    }
}
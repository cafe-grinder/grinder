package com.grinder.controller;

import com.grinder.domain.dto.ErrorResult;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class ExRestControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandle(IllegalArgumentException e) {
        log.error("[exceptionHandle] illegalExHandle", e);
        return new ErrorResult("wrong_input", e.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ErrorResult noSuchElementExHandle(EntityNotFoundException e) {
        log.error("[exceptionHandle] noSuchElementExHandle", e);
        return new ErrorResult("not_exist", e.getMessage());
    }
}

package com.grinder.controller;

import com.grinder.domain.dto.ErrorResult;
import com.grinder.exception.AlreadyExistException;
import com.grinder.exception.HasNotBeenAddedException;
import com.grinder.exception.LoginRequiredException;
import com.grinder.exception.MaximumRangeAlreadyAddedException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.PermissionDeniedDataAccessException;
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
    public ErrorResult noSuchElementExHandle(NoSuchElementException e) {
        log.error("[exceptionHandle] noSuchElementExHandle", e);
        return new ErrorResult("not_exist", e.getMessage());
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MaximumRangeAlreadyAddedException.class)
    public ErrorResult MaximumRangeExHandle(MaximumRangeAlreadyAddedException e) {
        log.error("[exceptionHandle] MaximumRangeExHandle", e);
        return new ErrorResult("Maximum_range", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HasNotBeenAddedException.class)
    public ErrorResult HasNotBeenAddedExHandle(HasNotBeenAddedException e) {
        log.error("[exceptionHandle] HasNotBeenAddedExHandle", e);
        return new ErrorResult("No_one", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LoginRequiredException.class)
    public ErrorResult LoginRequiredExHandle(LoginRequiredException e) {
        log.error("[exceptionHandle] LoginRequiredExHandle", e);
        return new ErrorResult("Login_Require", e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResult entityNotFoundExHandle(EntityNotFoundException e) {
        log.error("[exceptionHandle] entityNotFoundExHandle", e);
        return new ErrorResult("no_entity", e.getMessage());
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ErrorResult alreadyExistExHandle(AlreadyExistException e) {
        log.error("[exceptionHandle] alreadyExistExHandle", e);
        return new ErrorResult("already_exist", e.getMessage());
    }

    @ExceptionHandler(PermissionDeniedDataAccessException.class)
    public ErrorResult permissionDeniedDataAccessExHandle(PermissionDeniedDataAccessException e) {
        log.error("[exceptionHandle] permissionDeniedDataAccessExHandle", e);
        return new ErrorResult("has_not_authority", e.getMessage());
    }
}

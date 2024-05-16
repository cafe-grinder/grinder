package com.grinder.controller;

import com.grinder.domain.dto.ErrorResult;
import com.grinder.exception.*;

import java.util.NoSuchElementException;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.security.auth.login.LoginException;

@Slf4j
@ControllerAdvice
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandle(IllegalArgumentException e, Model model) {
        log.error("[exceptionHandle] illegalExHandle", e);
        return new ErrorResult("실패", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MaximumRangeAlreadyAddedException.class)
    public ErrorResult MaximumRangeExHandle(MaximumRangeAlreadyAddedException e, Model model) {
        log.error("[exceptionHandle] MaximumRangeExHandle", e);
        return new ErrorResult("실패", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HasNotBeenAddedException.class)
    public ErrorResult HasNotBeenAddedExHandle(HasNotBeenAddedException e, Model model) {
        log.error("[exceptionHandle] HasNotBeenAddedExHandle", e);
        return new ErrorResult("실패", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LoginRequiredException.class)
    public ErrorResult LoginRequiredExHandle(LoginRequiredException e, Model model) {
        log.error("[exceptionHandle] LoginRequiredExHandle", e);
        return new ErrorResult("실패", e.getMessage());
    }

    @ExceptionHandler(NoMoreContentException.class)
    public ResponseEntity<ErrorResult> NoMoreContentExHandle(NoMoreContentException e) {
        log.error("[exceptionHandle] NoMoreContentExHandle", e);
        return ResponseEntity.status(204).body(new ErrorResult("컨텐츠 없음", e.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchElementException.class)
    public ErrorResult NoSuchElementExceptionExHandle(NoSuchElementException e, Model model) {
        log.error("[exceptionHandle] NoSuchElementExHandle", e);
        return new ErrorResult("실패", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResult entityNotFoundExHandle(EntityNotFoundException e) {
        log.error("[exceptionHandle] entityNotFoundExHandle", e);
        return new ErrorResult("no_entity", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PermissionDeniedDataAccessException.class)
    public ErrorResult permissionDeniedDataAccessExHandle(PermissionDeniedDataAccessException e) {
        log.error("[exceptionHandle] permissionDeniedDataAccessExHandle", e);
        return new ErrorResult("has_not_authority", e.getMessage());
    }
  
    @ExceptionHandler(RecentAddedTagException.class)
    public ResponseEntity<ErrorResult> RecentAddedTagExHandle(RecentAddedTagException e) {
        log.error("[exceptionHandle] RecentAddedTagExHandle", e);
        return ResponseEntity.status(406).body(new ErrorResult("최근 추가됨", e.getMessage()));

    }
}

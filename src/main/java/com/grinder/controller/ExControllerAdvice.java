package com.grinder.controller;

import com.grinder.domain.dto.ErrorResult;
import com.grinder.exception.HasNotBeenAddedException;
import com.grinder.exception.LoginRequiredException;
import com.grinder.exception.MaximumRangeAlreadyAddedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
        model.addAttribute("message", e.getMessage());
        return new ErrorResult("실패", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MaximumRangeAlreadyAddedException.class)
    public String MaximumRangeExHandle(MaximumRangeAlreadyAddedException e, Model model) {
        log.error("[exceptionHandle] MaximumRangeExHandle", e);
        model.addAttribute("message", e.getMessage());
        return "error";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HasNotBeenAddedException.class)
    public String HasNotBeenAddedExHandle(HasNotBeenAddedException e, Model model) {
        log.error("[exceptionHandle] HasNotBeenAddedExHandle", e);
        model.addAttribute("message", e.getMessage());
        return "error";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LoginRequiredException.class)
    public String LoginRequiredExHandle(LoginRequiredException e, Model model) {
        log.error("[exceptionHandle] LoginRequiredExHandle", e);
        model.addAttribute("message", e.getMessage());
        return "error";
    }
}

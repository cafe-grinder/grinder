package com.grinder.controller;

import com.grinder.domain.dto.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalExHandle(IllegalArgumentException e, Model model) {
        log.error("[exceptionHandle] illegalExHandle", e);
        model.addAttribute("message", e.getMessage());
        return "error";
    }
}

package com.dmit.controller;

import com.dmit.exception.AlreadyExistsException;
import com.dmit.exception.InvalidOperation;
import com.dmit.exception.NotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WebExceptionHandler {
    @ExceptionHandler(value = {NotFoundException.class, AlreadyExistsException.class, InvalidOperation.class})
    public String exceptionHandler(Model model, Exception exception) {
        model.addAttribute("exceptionCaption", exception.getClass().getSimpleName());
        model.addAttribute("exceptionBody", exception.getMessage());

        return "exception_box";
    }
}

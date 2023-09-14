package com.dmit.controller;

import com.dmit.exception.AlreadyExistsException;
import com.dmit.exception.InvalidOperation;
import com.dmit.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class WebExceptionHandler {
    public static String chainedString(Throwable throwable) {
        StringBuilder SB = new StringBuilder(throwable.toString());
        while ((throwable = throwable.getCause()) != null)
            SB.append("<br>caused by ").append(throwable);
        return SB.toString();
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {
            NotFoundException.class,
            AlreadyExistsException.class,
            InvalidOperation.class
    })
    public String customExceptionHandler(Model model, Exception exception) {
        model.addAttribute("exceptionCaption", exception.getClass().getSimpleName());
        model.addAttribute("exceptionBody", exception.getMessage());

        return "exception_box";
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public String globalExceptionHandler(Model model, Exception exception) {
        model.addAttribute("exceptionCaption", exception.getClass().getSimpleName());
        model.addAttribute("exceptionBody", chainedString(exception));

        return "exception_box";
    }
}

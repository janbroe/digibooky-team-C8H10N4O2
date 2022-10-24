package com.switchfully.digibooky.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(NoSuchElementException.class)
    protected void noSuchElementException(NoSuchElementException exception, HttpServletResponse response) throws IOException {
        log.info("GET -> ".concat(exception.getMessage()));
        response.sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
}

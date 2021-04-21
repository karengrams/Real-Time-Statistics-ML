package com.api.realtimestatisticsml.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandleEntityUnparsedException {

    @ExceptionHandler(InvalidFormatException.class)
    protected ResponseEntity<Object> handleEntityUnparsed(
            InvalidFormatException exception) {
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

}

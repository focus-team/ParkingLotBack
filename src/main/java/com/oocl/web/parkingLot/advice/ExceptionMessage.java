package com.oocl.web.parkingLot.advice;

import com.oocl.web.parkingLot.exception.GlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionMessage {

    @ExceptionHandler(value = GlobalException.class)
    public ResponseEntity exceptionHandler(GlobalException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getErrMessage());
    }

}


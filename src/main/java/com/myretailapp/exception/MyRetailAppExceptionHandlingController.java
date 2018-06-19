package com.myretailapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyRetailAppExceptionHandlingController {

    @ExceptionHandler(UnExpectedException.class)
    public ResponseEntity<MyRetailAppExceptionResponse> timeoutException(UnExpectedException ex) {
        MyRetailAppExceptionResponse response = new MyRetailAppExceptionResponse();
        response.setErrorCode("INTERNAL_ERROR");
        response.setErrorMessage(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.GATEWAY_TIMEOUT);
    }

}

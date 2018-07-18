package com.myretailapp.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class MyRetailAppExceptionHandlingController {

    @ExceptionHandler(UnExpectedException.class)
    ResponseEntity<MyRetailAppExceptionResponse> unExpectedExceptionHandler(UnExpectedException ex) {
        new ResponseEntity<>(new MyRetailAppExceptionResponse('INTERNAL_ERROR', ex.errorMessage), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(MyRetailAppTimeoutException.class)
    ResponseEntity<MyRetailAppExceptionResponse> timeoutExceptionHandler(MyRetailAppTimeoutException ex) {
        new ResponseEntity<>(new MyRetailAppExceptionResponse('TIMEOUT', ex.errorMessage), HttpStatus.GATEWAY_TIMEOUT)
    }
}

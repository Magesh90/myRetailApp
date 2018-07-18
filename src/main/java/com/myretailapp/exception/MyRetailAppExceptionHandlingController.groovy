package com.myretailapp.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class MyRetailAppExceptionHandlingController {

    @ExceptionHandler(UnExpectedException.class)
    ResponseEntity<MyRetailAppExceptionResponse> unExpectedExceptionHandler(UnExpectedException ex) {
        MyRetailAppExceptionResponse response = new MyRetailAppExceptionResponse()
        response.setErrorCode("INTERNAL_ERROR")
        response.setErrorMessage(ex.getErrorMessage())
        new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(MyRetailAppTimeoutException.class)
    ResponseEntity<MyRetailAppExceptionResponse> timeoutExceptionHandler(MyRetailAppTimeoutException ex) {
        MyRetailAppExceptionResponse response = new MyRetailAppExceptionResponse()
        response.setErrorCode("TIMEOUT")
        response.setErrorMessage(ex.getErrorMessage())

        new ResponseEntity<>(response, HttpStatus.GATEWAY_TIMEOUT)
    }
}

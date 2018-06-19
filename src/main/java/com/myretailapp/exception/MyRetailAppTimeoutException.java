package com.myretailapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
public class MyRetailAppTimeoutException extends RuntimeException {

    private String errorMessage;
    public MyRetailAppTimeoutException(String message){
        super(message);
        this.errorMessage = message;
    }
}

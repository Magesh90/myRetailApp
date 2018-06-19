package com.myretailapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@ResponseBody
public class UnExpectedException extends RuntimeException {
    String errorMessage;

    public UnExpectedException(String message) {
        super(message);
        this.errorMessage = message;
    }


    public String getErrorMessage() {
        return errorMessage;
    }
}

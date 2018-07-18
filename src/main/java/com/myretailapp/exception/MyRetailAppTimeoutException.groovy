package com.myretailapp.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
class MyRetailAppTimeoutException extends RuntimeException {

    private String errorMessage

    MyRetailAppTimeoutException(String message) {
        super(message)
        this.errorMessage = message
    }

}

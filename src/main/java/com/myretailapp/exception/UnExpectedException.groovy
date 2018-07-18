package com.myretailapp.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@ResponseBody
class UnExpectedException extends RuntimeException {
    String errorMessage

    UnExpectedException(String message) {
        super(message)
        this.errorMessage = message
    }

}

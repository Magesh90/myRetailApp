package com.myretailapp.exception

class MyRetailAppExceptionResponse {

    String errorCode
    String errorMessage

    MyRetailAppExceptionResponse(String errorCode, String errorMessage) {
        this.errorCode = errorCode
        this.errorMessage = errorMessage
    }

}

package com.rakib.exceptionhandling.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private String errorCode;
    private String message;

    public BaseException(String message, String errorCode) {
        this.errorCode = errorCode;
        this.message = message;
    }
}

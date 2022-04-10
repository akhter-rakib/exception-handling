package com.rakib.exceptionhandling.exception;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException(String message, String erroCode) {
        super(message, erroCode);
    }
}

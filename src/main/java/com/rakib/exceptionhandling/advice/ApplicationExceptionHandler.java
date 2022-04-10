package com.rakib.exceptionhandling.advice;

import com.rakib.exceptionhandling.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler {
    private static final String DEFAULT_ERROR_CODE = "01_999_999";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return errorMap;
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> handleBaseException(Throwable ex) {
        log.warn(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(prepareErrorResponse(ex));
    }

    private ErrorResponse prepareErrorResponse(Throwable e) {
        ErrorResponse errorResponse = new ErrorResponse();
        if (e instanceof BaseException) {
            errorResponse.setErrorCode(((BaseException) e).getErrorCode());
        } else {
            errorResponse.setErrorCode(DEFAULT_ERROR_CODE);
        }
        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }
}

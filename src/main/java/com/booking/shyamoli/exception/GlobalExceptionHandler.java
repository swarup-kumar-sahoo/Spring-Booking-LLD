package com.booking.shyamoli.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError>
    handleRuntimeException(
            RuntimeException ex
    ){

        ApiError error =
                new ApiError(
                        ex.getMessage(),
                        HttpStatus.BAD_REQUEST.value()
                );

        return ResponseEntity
                .badRequest()
                .body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError>
    handleException(
            Exception ex
    ){

        ApiError error =
                new ApiError(
                        ex.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value()
                );

        return ResponseEntity
                .status(
                        HttpStatus.INTERNAL_SERVER_ERROR
                )
                .body(error);
    }
}

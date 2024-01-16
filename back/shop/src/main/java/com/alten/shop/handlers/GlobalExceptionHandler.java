package com.alten.shop.handlers;

import com.alten.shop.exceptions.ObjectNotValidException;
import com.alten.shop.models.ErrorModel;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorModel handleException(EntityNotFoundException e) {
        return ErrorModel.builder()
                .timestamp(new Date())
                .httpCode(HttpStatus.NOT_FOUND)
                .error("Entity Not Found")
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(ObjectNotValidException.class)
    public ResponseEntity<?> handleException(ObjectNotValidException e) {
        return ResponseEntity
                .badRequest()
                .body(e.getErrorMessages());
    }


    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<ErrorModel> handleException(HttpClientErrorException.BadRequest e) {
        ErrorModel err = ErrorModel.builder()
                .timestamp(new Date())
                .httpCode(HttpStatus.BAD_REQUEST)
                .error("Bad Request")
                .message(e.getMessage())
                .build();
        return ResponseEntity.badRequest().body(err);
    }
}

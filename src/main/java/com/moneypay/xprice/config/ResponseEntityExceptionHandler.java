package com.moneypay.xprice.config;

import com.moneypay.xprice.controller.dto.base.BaseResponse;
import com.moneypay.xprice.controller.dto.base.Result;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@Slf4j
@ControllerAdvice
public class ResponseEntityExceptionHandler {

    private final String badRequestCode = String.valueOf(HttpStatus.BAD_REQUEST.value());
    private final String notFoundCode = String.valueOf(HttpStatus.NOT_FOUND.value());
    private final String unhandledResponseCode = String.valueOf(-1);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(final EntityNotFoundException ex) {
        return ResponseEntity.badRequest()
                .body(BaseResponse.builder()
                        .result(Result.instance(notFoundCode, ex.getMessage()))
                        .build());
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<Object> handleEntityExistsException(final EntityExistsException ex) {
        return ResponseEntity.badRequest()
                .body(BaseResponse.builder()
                        .result(Result.instance(badRequestCode, ex.getMessage()))
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnhandledExceptions(final Exception ex) {
        final var uuid = UUID.randomUUID();
        log.error("[{}] - Unhandled exception", uuid, ex);

        return ResponseEntity.internalServerError()
                .body(BaseResponse.builder()
                        .result(Result.instance(unhandledResponseCode,
                                "Error occurred, please contact the team - " + uuid))
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex) {
        final var message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(System.lineSeparator()));

        return ResponseEntity.badRequest()
                .body(BaseResponse.builder()
                        .result(Result.instance(badRequestCode, message))
                        .build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        return ResponseEntity.badRequest()
                .body(BaseResponse.builder()
                        .result(Result.instance(badRequestCode, ex.getMessage()))
                        .build());
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Object> handleHandlerMethodValidationException(HandlerMethodValidationException ex) {
        final var message = ex.getAllErrors()
                .stream()
                .map(MessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(System.lineSeparator()));

        return ResponseEntity.badRequest()
                .body(BaseResponse.builder()
                        .result(Result.instance(badRequestCode, message))
                        .build());
    }
}

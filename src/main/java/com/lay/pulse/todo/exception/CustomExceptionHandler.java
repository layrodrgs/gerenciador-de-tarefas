package com.lay.pulse.todo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionMessage exception = getExceptionMessageObject(ex);
        return handleExceptionInternal(ex, exception, headers, exception.getStatusCode(), request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";
        ExceptionMessage exception = new ExceptionMessage(error, HttpStatus.BAD_REQUEST, null);
        return new ResponseEntity<>(exception, new HttpHeaders(), exception.getStatusCode());
    }

    @ExceptionHandler({ EntityNotFoundException.class })
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        ExceptionMessage exception = new ExceptionMessage(ex.getMessage(), HttpStatus.NOT_FOUND, null);
        return handleExceptionInternal(ex, exception, new HttpHeaders(), exception.getStatusCode(), request);
    }

    @ExceptionHandler({ DuplicatedEntityException.class })
    public ResponseEntity<Object> handleDuplicatedEntityException(DuplicatedEntityException ex, WebRequest request) {
        ExceptionMessage exception = new ExceptionMessage(ex.getMessage(), HttpStatus.BAD_REQUEST, null);
        return handleExceptionInternal(ex, exception, new HttpHeaders(), exception.getStatusCode(), request);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        ExceptionMessage exception = new ExceptionMessage(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        return new ResponseEntity<>(exception, new HttpHeaders(), exception.getStatusCode());
    }

    private ExceptionMessage getExceptionMessageObject(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(field -> errors.add(field.getField() + ": " + field.getDefaultMessage()));
        ex.getBindingResult().getGlobalErrors().forEach(field -> errors.add(field.getObjectName() + ": " + field.getDefaultMessage()));
        return new ExceptionMessage("Validation Error", HttpStatus.BAD_REQUEST, errors);
    }

}

package com.lay.pulse.todo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@AllArgsConstructor
public class ExceptionMessage {

    private String message;
    private HttpStatus statusCode;
    private List<String> errors;
}

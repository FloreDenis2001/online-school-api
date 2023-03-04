package com.example.onlineschoolapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StatusCourse extends RuntimeException{
    public StatusCourse(String message) {
        super(message);
    }
}

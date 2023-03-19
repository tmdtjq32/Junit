package me.tmdtjq32.myproject.src.exception;

import org.springframework.http.HttpStatus;

public interface BaseError {
    Integer getCode();
    HttpStatus getHttpStatus();
    String getMessage();
}

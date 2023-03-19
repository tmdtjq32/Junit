package me.tmdtjq32.myproject.src.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class APIException extends RuntimeException {

    private final BaseErrorCode baseErrorCode;
    private final String detail;
}

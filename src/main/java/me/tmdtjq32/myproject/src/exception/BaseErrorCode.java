package me.tmdtjq32.myproject.src.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BaseErrorCode implements BaseError {

    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, 401 ,"Invalid Parameter Included"),
    NO_RESULT(HttpStatus.NOT_FOUND, 404 ,"There is No Result"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500 ,"Internal server error"),
    ;

    private final HttpStatus httpStatus;
    private final Integer code;
    private final String message;
}

package me.tmdtjq32.myproject.src.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class APIException extends RuntimeException {

    private final BaseErrorCode baseErrorCode;
}

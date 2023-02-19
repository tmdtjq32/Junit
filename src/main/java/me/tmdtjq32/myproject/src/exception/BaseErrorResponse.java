package me.tmdtjq32.myproject.src.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class BaseErrorResponse {

    private final String error;
    private final Integer code;
    private final String message;


}

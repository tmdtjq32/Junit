package me.tmdtjq32.myproject.src.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class BaseErrorResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String name;
    private final Integer code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String subMessage;
}

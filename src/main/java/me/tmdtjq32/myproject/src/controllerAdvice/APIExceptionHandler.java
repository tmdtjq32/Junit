package me.tmdtjq32.myproject.src.controllerAdvice;

import me.tmdtjq32.myproject.src.exception.APIException;
import me.tmdtjq32.myproject.src.exception.BaseErrorCode;
import me.tmdtjq32.myproject.src.exception.BaseErrorResponse;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(APIException.class)
    public BaseErrorResponse handleAPIException(APIException e){
        BaseErrorCode baseErrorCode = e.getBaseErrorCode();
        return BaseErrorResponse.builder()
                .code(baseErrorCode.getCode())
                .message(baseErrorCode.getMessage())
                .build();
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseErrorResponse handleRuntimeException(RuntimeException e) {
        return BaseErrorResponse.builder()
                .error(e.getClass().getName())
                .code(BaseErrorCode.INTERNAL_SERVER_ERROR.getCode())
                .message(e.getMessage())
                .build();
    }


}

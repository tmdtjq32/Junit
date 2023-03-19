package me.tmdtjq32.myproject.src.filter;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class loggingIO {



    public static void loggingRequest(ServletRequest request) {
        try{
            HttpServletRequest req = (HttpServletRequest) request;
            ContentCachingRequestWrapper wrapper = new ContentCachingRequestWrapper(req);
            String body = new String(wrapper.getContentAsByteArray());
            log.info("Request logging : {} {} Header :  body : {}"
                    ,wrapper.getMethod(), wrapper.getRequestURI(),body);
        }catch(Exception e){
            log.error("logging filter error : {}",e.getMessage());
        }
    }

    public static void loggingResponse(ServletResponse response) {
        HttpServletResponse res = (HttpServletResponse) response;

    }
}

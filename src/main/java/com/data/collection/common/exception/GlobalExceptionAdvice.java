package com.data.collection.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * 异常统一处理
 *
 * @author tianslic
 * @version 2019-04-08
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    Logger logger = LoggerFactory.getLogger(Exception.class);

    @ExceptionHandler(SessionExpireException.class)
    public void loginTimeout(HttpServletResponse response) {
        logger.warn("session失效");
        response.setStatus(401);
    }

}

package com.example.dmsserver.commons.exception;

import com.example.dmsserver.commons.result.JSONResult;
import com.example.dmsserver.commons.result.ResponseStatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.HashMap;
import java.util.Map;

/**
 * 统一异常拦截处理
 * 可以针对异常的类型进行捕获，然后返回json信息到前端
 */
@RestControllerAdvice
public class GraceExceptionHandler {

    @ExceptionHandler(MyCustomException.class)
    public JSONResult returnMyException(MyCustomException e) {
        e.printStackTrace();
        return JSONResult.exception(e.getResponseStatusEnum());
    }


    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JSONResult validationError(BindException ex){
        Map<String,String> errorMsg = new HashMap<>();
        for (FieldError fieldError : ex.getFieldErrors()) {
            errorMsg.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new JSONResult(ResponseStatusEnum.REQUEST_PARAM_ERROR,errorMsg);
    }

//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    @ExceptionHandler(value = ShiroException.class)
//    public JSONResult handler(ShiroException e) {
//        return new JSONResult(ResponseStatusEnum.NO_AUTH);
//    }

}

package com.volunteer.commonweal.controllers.exceptionsHandler;

import com.volunteer.commonweal.models.exceptionModels.*;
import com.volunteer.commonweal.models.requestModels.ExceptionConvetModel.ExceptionEntity;
import com.volunteer.commonweal.services.env.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionsHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionsHandler.class);
    @Autowired
    private Config config;

    //处理BaseException的错误
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    ResponseEntity AuthExceptionHandler(HttpServletRequest request,BaseException e) {
        return new ResponseEntity(getExceptionEntity(e),getHttpStatus(request, e));
    }

    @ExceptionHandler(value = EncryptException.class)
    @ResponseBody
    ResponseEntity EncryptExceptionHandler(){
        return new ResponseEntity("EncryptionUtils Error",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = EmailException.class)
    @ResponseBody
    ResponseEntity EmailExceptionHandler(HttpServletRequest request,BaseException e){
        return new ResponseEntity(getExceptionEntity(e),getHttpStatus(request, e));
    }

    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    ResponseEntity handleNpe(HttpServletRequest request, NullPointerException e) {
        logger.error(e.getMessage());
        BaseException npe = new BaseException(1041, config.getExceptionsMap().get(1041));
        ExceptionEntity exceptionEntity = new ExceptionEntity(npe);
        return new ResponseEntity<>(exceptionEntity, getHttpStatus(request, npe));
    }

    //获取HttpStatus
    private HttpStatus getHttpStatus(HttpServletRequest request, BaseException e){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            if (e.getClass().equals(AuthException.class)) {
                return HttpStatus.FORBIDDEN;
            }else if(e.getCause().equals(EmailException.class)){
                return HttpStatus.OK;
            } else return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.valueOf(statusCode);
    }

    //错误信息转化
    private ExceptionEntity getExceptionEntity(BaseException e) {
        return new ExceptionEntity(e);
    }

}

package com.volunteer.commonweal.models.exceptionModels;

public class BaseException extends Throwable{
    public int code; //异常代码
    public String message; //异常信息
    public BaseException(){
    }
    public BaseException(Throwable e){
        this.message = e.getMessage();
    }
    public BaseException(int code,String message){
        this.code = code;
        this.message = message;
    }
}

package com.volunteer.commonweal.models.requestModels.ExceptionConvetModel;

import com.volunteer.commonweal.models.exceptionModels.BaseException;

public class ExceptionEntity {
    public String message;
    public int code;

    public ExceptionEntity(BaseException exception) {
        this.message = exception.message;
        this.code = exception.code;
    }
}

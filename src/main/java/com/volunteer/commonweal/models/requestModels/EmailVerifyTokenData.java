package com.volunteer.commonweal.models.requestModels;

public class EmailVerifyTokenData {
    public String token; //验证码
    public String email; //邮箱
    public Boolean isTokenCorrect;//验证码是否正确
}

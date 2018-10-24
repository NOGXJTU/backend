package com.volunteer.commonweal.models.implementModels.homePageModels;

import com.volunteer.commonweal.constants.DataBaseConst;
import com.volunteer.commonweal.models.BaseModel;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = DataBaseConst.EMAILVERIFYTOKEN_DATABASE_NAME)
public class EmailVerifyToken extends BaseModel {
    @Indexed(unique = true)
    @NotNull
    private String email;//邮箱
    @NotNull
    private String token;//验证码
    @NotNull
    private Long timeStamp;//时间戳

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public String getToken() {
        return token;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

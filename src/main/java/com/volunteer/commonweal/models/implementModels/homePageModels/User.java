package com.volunteer.commonweal.models.implementModels.homePageModels;

import com.volunteer.commonweal.constants.DataBaseConst;
import com.volunteer.commonweal.models.BaseModel;
import com.volunteer.commonweal.models.exceptionModels.EncryptException;
import com.volunteer.commonweal.common.EncryptionUtils;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

//用户类
@Document(collection = DataBaseConst.USER_DATABASE_NAME)
public class User extends BaseModel {
    @Indexed(unique = true)
    @NotNull
    private String username; //用户名
    @NotNull
    private String name; //用户真实姓名
    @NotNull
    private String password; //密码
    @Indexed(unique = true)
    @NotNull
    private String email; //邮箱
    @Indexed(unique = true)
    @NotNull
    private String phone; //手机号

    private String description; //用户描述(有默认值)
    @NotNull
    private String qq; //qq号
    @NotNull
    private String school; //学校
    @NotNull
    private Boolean isSuperUser; //是否为超级管理员

    private String avatar; //用户头像 暂时定下存储的在服务器本地的预制图像(有默认值)

    private List<String> activitiesId = new ArrayList(); //参加的活动列表

    private String organization;

    public String getOrganization() {
        return organization;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    // 不加的操作 -- 不能获取到用户的密码
//    public String getPassword() {
//        return "";
//    }

    public String getDescription() {
        return description;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getPhone() {
        return phone;
    }

    public String getQq() {
        return qq;
    }

    public String getSchool() {
        return school;
    }

    public Boolean isSuperUser() {
        return isSuperUser;
    }

    public List<String> getActivitiesId() {
        return activitiesId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setSuperUser(Boolean superUser) {
        isSuperUser = superUser;
    }

    public void setActivitiesId(List<String> activitiesId) {
        this.activitiesId = activitiesId;
    }

    public void setPassword(String password) throws EncryptException {
        this.password = EncryptionUtils.userPasswordEncrypt("SHA1", password);
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public boolean isRightPassword(String password) throws EncryptException {
        return this.password.equals(EncryptionUtils.userPasswordEncrypt("SHA1", password));
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

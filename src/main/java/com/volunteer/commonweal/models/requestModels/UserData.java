package com.volunteer.commonweal.models.requestModels;


import java.util.List;

public class UserData {
    public String userId;//用户id
    public List<String> userIdGroup;//批量用户id
    public String username;//用户名
    public String passport;//用户登录凭证(用户名或者邮箱或者手机号)
    public String name;//真实姓名
    public String password;//密码
    public String newPassword;//新密码
    public String email;//邮箱
    public String phone;//手机号
    public String description;//用户描述
    public String qq;//QQ号
    public String school;//学校
    public Boolean isSuperUser;//是否为超级管理员
    public String avatar;//用户头像
    public List<String> activitesId;//活动Id
    public Boolean duplicateFlag;//电话|邮箱|用户名是否重复
    public String token;//邮箱验证码
}

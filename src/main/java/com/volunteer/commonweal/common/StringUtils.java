package com.volunteer.commonweal.common;

import org.springframework.stereotype.Component;


public class StringUtils {
    public static final boolean isNumber(String string){
        try {
            Integer.parseInt(string);
            return true;
        }catch (NumberFormatException e){
        }
        try {
            Double.parseDouble(string);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
    //正则判断用户名格式
    public static boolean isUsernameFormat(String username){
        return true;
    }

    //正则判断密码格式
    public static boolean isPasswordFormat(String password){
        return true;
    }

    //正则判断真实姓名格式
    public static boolean isNameFormat(String name){return true;}

    //正则判断邮箱格式
    public static boolean isEmailFormat(String email){return true;}

    //正则判断活动申请描述格式
    public static boolean isApplicationDescription(String description){
        return true;
    }

    //电话格式不用正则判断(用isNumber就可以了)

    //学校格式不用判断应该查找学校列表
    public static boolean isInSchoolList(String school){return true;}
}

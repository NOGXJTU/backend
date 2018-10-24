package com.volunteer.commonweal.common;

import com.volunteer.commonweal.constants.ModelDataConst;
import com.volunteer.commonweal.constants.UIConst;

// 字符串是否符合规则的约束, 不涉及到数据库操作的约束判断
public class ParamConstraintUtils {
    public static final boolean isUsernameValid(String username){
        return (username.length() <= ModelDataConst.MAX_USERNAME_LENGTH)&&
                (username.length() >= ModelDataConst.MIN_USERNAME_LENGTH) &&
                StringUtils.isUsernameFormat(username);
    }
    public static final boolean isPasswordValid(String password){
        return (password.length() <= ModelDataConst.MAX_PASSWORD_LENGTH)&&
                (password.length() >= ModelDataConst.MIN_PASSWORD_LENGTH)&&
                StringUtils.isPasswordFormat(password);
    }
    public static final boolean isNameValid(String name){
        return (name.length() <= ModelDataConst.MAX_NAME_LENGTH) &&
                (name.length() >= ModelDataConst.MIN_NAME_LENGTH);
    }
    public static final boolean isEmailValid(String email){
        return (email.contains("@")
                &&StringUtils.isEmailFormat(email));
    }
    public static final boolean isPhoneValid(String phone){
        return (phone.length() == ModelDataConst.ALLOWED_PHONE_LENGTH&&
                StringUtils.isNumber(phone));
    }
    public static final boolean isSchoolValid(String school){
        return (StringUtils.isInSchoolList(school));
    }
    public static final boolean isQQValid(String qq){
        return (qq.length() >= ModelDataConst.MIN_QQ_LENGTH && qq.length() <= ModelDataConst.MAX_QQ_LENGTH && StringUtils.isNumber(qq));
    }
    public static final boolean isTimeExceed(Long time){
        return (System.currentTimeMillis()/1000 - time > UIConst.TOKEN_VALID_TIME);
    }
    public static final boolean isDescriptionValid(String description){
        return (description.length() <= ModelDataConst.MAX_APPLICATION_DESCRIPTION_LENGTH) &&
                StringUtils.isApplicationDescription(description);
    }
    //活动只能是超级管理员发布所以暂时限制较少
    public static final boolean isActivityNameValid(String name){
        return (name.length() <= ModelDataConst.MAX_ACTIVITY_NAME_LENGTH && name.length() >= ModelDataConst.MIN_ACTIVITY_NAME_LENGTH);
    }
    public static final boolean isActivityDescriptionValid(String description){
        return (description.length() <= ModelDataConst.MAX_ACTIVITY_DESCRIPTION_LENGTH);
    }
    public static final boolean isTokenValid(String token){
        return (token.length() == UIConst.TOKEN_LENGTH);
    }
    public static final boolean isActivityTypeValid(String type){
        return ModelDataConst.ACTIVITY_TYPE_LIST.contains(type);
    }
    public static final boolean isPhone(String phone){
        return isPhoneValid(phone);
    }
    public static final boolean isEmail(String email){
        return isEmailValid(email);
    }

    public static final boolean isGradeValid(String grade){
        return ModelDataConst.GRADES.contains(grade);
    }
    public static final boolean isCollegeTypeValid(String type){
        return ModelDataConst.COLLEGE_LEVEL.contains(type);
    }
    public static final boolean isSubjectValid(String grade, String subject){
        switch (grade){
            case "一年级":
            case "二年级":
                return ModelDataConst.SUBJECTS_ONE_TO_TWO.contains(subject);
            case "三年级":
            case "四年级":
            case "五年级":
            case "六年级":
                return ModelDataConst.SUBJECTS_THREE_TO_SIX.contains(subject);
            case "七年级":
            case "八年级":
            case "九年级":
            case "十年级":
            case "十一年级":
            case "十二年级":
            case "十三年级":
                return ModelDataConst.SUBJECTS_SEVEN_TO_TWELVE.contains(subject);
            default:
                return false;
        }
    }
}

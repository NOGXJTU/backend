package com.volunteer.commonweal.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//TODO:1.考虑使用findAndModify 和 update解决一部分事务
public class ModelDataConst {
    public static final int MAX_USERNAME_LENGTH = 16;
    public static final int MIN_USERNAME_LENGTH = 6;
    public static final int MAX_PASSWORD_LENGTH = 20;
    public static final int MIN_PASSWORD_LENGTH = 8;
    public static final int ALLOWED_PHONE_LENGTH = 11;
    public static final int MAX_NAME_LENGTH = 20;
    public static final int MIN_NAME_LENGTH = 2;
    public static final int MAX_QQ_LENGTH = 11;
    public static final int MIN_QQ_LENGTH = 5;
    public static final int MAX_APPLICATION_DESCRIPTION_LENGTH = 500;
    public static final int MAX_ACTIVITY_NAME_LENGTH = 20;
    public static final int MIN_ACTIVITY_NAME_LENGTH = 4;
    public static final int MAX_ACTIVITY_DESCRIPTION_LENGTH = 2000;
    public static final List<String> ACTIVITY_TYPE_LIST = Arrays.asList("文化课","兴趣课","思想沙龙","公益晚会","其他");
    public static final List<String> GRADES = Arrays.asList("一年级", "二年级", "三年级", "四年级", "五年级", "六年级", "七年级", "八年级", "九年级", "十年级", "十一年级", "十二年级");
    public static final List<String> SUBJECTS_ONE_TO_TWO = Arrays.asList("语文", "数学");
    public static final List<String> SUBJECTS_THREE_TO_SIX = Arrays.asList("语文", "数学", "英语", "其他");
    public static final List<String> SUBJECTS_SEVEN_TO_TWELVE = Arrays.asList("语文", "数学", "英语","政治","地理","生物","历史","地理");
    public static final List<String> COLLEGE_LEVEL = Arrays.asList("985", "211", "一本", "二本", "其他", "海外高校");

    //默认值
    public static final String DEFAULT_AVATAR = "";//默认头像路径
    public static final String DEFAULT_DESCRIPTION = "";//默认用户描述
    public static final boolean DEFAULT_FINISHED = false;//默认活动状态
    public static final int DEFAULT_APPLICATION_STATUS = 0;//默认申请状态
    public static final String DEFAULT_CODING_FORMAT = "utf-8";//默认编码模式为utf-8

}

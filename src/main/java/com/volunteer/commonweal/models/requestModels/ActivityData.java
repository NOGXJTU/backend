package com.volunteer.commonweal.models.requestModels;

import java.util.List;

public class ActivityData {
    public String name; //活动名称
    public String ownerId;//组长id
    public String description;//活动描述
    public String place;//活动地点
    public Boolean finished;//活动状态
    public String beginTime;//开始时间
    public String lasting;//持续时间
    public List<String> userGroup;//全部成员列表
    public String userId;//成员Id
    public String newOwnerId;//转让给新的组长的Id
    public String activityId;//单条活动时候申请的Id
    public List<String> userIdGroup;//批量删除的时候使用的用户Id组
    public List<String> activityIdGroup;//批量活动时候申请的Id
    public Boolean isDuplicate;//是否重复
}

package com.volunteer.commonweal.models.implementModels.homePageModels;

import com.volunteer.commonweal.constants.DataBaseConst;
import com.volunteer.commonweal.models.BaseModel;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = DataBaseConst.APPLICATION_DATABASE_NAME)
public class Application extends BaseModel {
    @Indexed
    @NotNull
    private String userId; //用户Id
    @Indexed
    @NotNull
    private String activityId; //活动Id
    private String description; //申请理由
    @NotNull
    private Integer status; //申请状态 0:申请中 1:通过 2:拒绝

    public String getUserId() {
        return userId;
    }

    public String getActivityId() {
        return activityId;
    }

    public String getDescription() {
        return description;
    }

    public Integer getStatus() { return status; }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setStatus(Integer status) { this.status = status; }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

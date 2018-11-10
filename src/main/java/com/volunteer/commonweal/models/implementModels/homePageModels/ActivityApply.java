package com.volunteer.commonweal.models.implementModels.homePageModels;

import com.volunteer.commonweal.constants.DataBaseConst;
import com.volunteer.commonweal.models.BaseModel;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = DataBaseConst.ACTIVITYAPPLY_DATABASE_NAME)
public class ActivityApply extends BaseModel {

    @NotNull
    private String organizationId;

    @Indexed
    @NotNull
    private String name;

    @Indexed
    @NotNull
    private String ownerId;

    private String description;

    @NotNull
    private String place;

    @NotNull
    private String beginTime;

    @NotNull
    private String lasting;

    @NotNull
    private String type;

    private String picUrl;//图片封面路径

    @NotNull
    private int status;

    private String applyDescription;

    public String getApplyDescription() {
        return applyDescription;
    }

    public int getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public String getLasting() {
        return lasting;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public String getPlace() {
        return place;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public void setLasting(String lasting) {
        this.lasting = lasting;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setApplyDescription(String applyDescription) {
        this.applyDescription = applyDescription;
    }
}

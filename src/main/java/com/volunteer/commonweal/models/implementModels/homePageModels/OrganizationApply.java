package com.volunteer.commonweal.models.implementModels.homePageModels;

import com.volunteer.commonweal.constants.DataBaseConst;
import com.volunteer.commonweal.models.BaseModel;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = DataBaseConst.ORGANIZATIONAPPLY_DATABASE_NAME)
public class OrganizationApply extends BaseModel {
    @Indexed
    @NotNull
    private String organizationId;

    @Indexed
    @NotNull
    private String userId;

    private String description; //申请理由

    @NotNull
    private Integer status; //申请状态 0:申请中 1:通过 2:拒绝

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

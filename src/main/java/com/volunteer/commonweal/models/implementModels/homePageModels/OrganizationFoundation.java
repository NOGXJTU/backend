package com.volunteer.commonweal.models.implementModels.homePageModels;

import com.volunteer.commonweal.constants.DataBaseConst;
import com.volunteer.commonweal.models.BaseModel;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = DataBaseConst.ORGANIZATIONFOUNDATION_DATABASE_NAME)
public class OrganizationFoundation extends BaseModel {
    @Indexed(unique = true)
    @NotNull
    private String name;

    @Indexed
    @NotNull
    private String leaderId; //团队创始人ID

    private String description;

    private String location;

    @NotNull
    private int status;

    private String applyDescription;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setApplyDescription(String applyDescription) {
        this.applyDescription = applyDescription;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getStatus() {
        return status;
    }

    public String getApplyDescription() {
        return applyDescription;
    }
}

package com.volunteer.commonweal.models.implementModels.homePageModels;

import com.volunteer.commonweal.constants.DataBaseConst;
import com.volunteer.commonweal.models.BaseModel;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Document(collection = DataBaseConst.ORGANIZATION_DATABASE_NAME)
public class Organization extends BaseModel {
    @Indexed
    @NotNull
    private String name;

    @Indexed
    @NotNull
    private String leaderId; //团队创始人ID

    private String description;

    private String location;

    private List<User> users; //成员列表

    private List<Activity> activities; //组织的活动列表

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public List<User> getUsers() {
        return users;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public String getLocation() {
        return location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

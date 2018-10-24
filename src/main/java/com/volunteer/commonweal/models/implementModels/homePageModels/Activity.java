package com.volunteer.commonweal.models.implementModels.homePageModels;

import com.volunteer.commonweal.common.GzipUtils;
import com.volunteer.commonweal.constants.DataBaseConst;
import com.volunteer.commonweal.constants.ModelDataConst;
import com.volunteer.commonweal.models.BaseModel;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

//活动类
@Document(collection = DataBaseConst.ACTIVITY_DATABASE_NAME)
public class Activity extends BaseModel {
//    PS:活动名称和活动时间
    @NotNull
    private List<String> users;//成员
    @Indexed
    @NotNull
    private String name;//活动名称
    @Indexed
    @NotNull
    private String ownerId;//组长id
    @NotNull
    private String description;//活动描述
    @NotNull
    private String place;//活动地点
    @NotNull
    private Boolean finished;//活动状态 (默认为false 即没有完成)
    @NotNull
    private String beginTime;//开始时间
    @NotNull
    private String type;//类别
    @NotNull
    private String lasting;//持续时间
    private String picUrl;//图片封面路径
    private byte[] comment;//活动总结
    private Boolean show;//是否展示
    private List<String> pictures;//展示图片集合路径

    public String getType(){
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getPlace() {
        return place;
    }

    public Boolean isFinished() {
        return finished;
    }

    public List<String> getUsers() {
        return users;
    }

    public String getBeginTime() { return beginTime; }

    public String getLasting() { return lasting; }

    public Boolean getFinished() {
        return finished;
    }

    public Boolean getShow() {
        return show;
    }

    public String getComment() {
        return GzipUtils.uncompress(this.comment, ModelDataConst.DEFAULT_CODING_FORMAT);
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setFinished(Boolean finished) { this.finished = finished; }

    public void setBeginTime(String beginTime) { this.beginTime = beginTime; }

    public void setLasting(String lasting) { this.lasting = lasting; }

    public void setType(String type) {
        this.type = type;
    }

    public void setComment(String comment) {
        this.comment = GzipUtils.compress(comment, ModelDataConst.DEFAULT_CODING_FORMAT);
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

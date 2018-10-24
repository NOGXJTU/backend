package com.volunteer.commonweal.models.implementModels.resourcesSharingModels;

import com.volunteer.commonweal.common.DateUtils;
import com.volunteer.commonweal.constants.DataBaseConst;
import com.volunteer.commonweal.models.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = DataBaseConst.VIDEOCLASS_DATABASE_NAME)
public class VideoClass extends BaseModel {
    private String name; //典型例题名字
    private String file; //典型例题视频dir
    private String image; //典型例题封面图片dir
    private String grade; //年级
    private String subject; //学科
    private String description; //描述
    private String teacher;//老师
    private String uploadTime; //日期

    public String getSubject() {
        return subject;
    }

    public String getGrade() {
        return grade;
    }

    public String getDescription() {
        return description;
    }

    public String getFile() {
        return file;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public String getName() {
        return name;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public void updateUploadTime(){
        this.uploadTime = DateUtils.getCurrentTime();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public String getTeacher() {
        return teacher;
    }


    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setImage(String image) {
        this.image = image;
    }

}

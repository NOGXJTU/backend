package com.volunteer.commonweal.models.implementModels.resourcesSharingModels;

import com.volunteer.commonweal.common.DateUtils;
import com.volunteer.commonweal.constants.DataBaseConst;
import com.volunteer.commonweal.models.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;

//知识点()
// TODO:解决文件上传下载需要一个单独服务器的问题: 因为数据库使用的是相同的所以只需要将模型和Repository重新创建一个项目在上上面单独进行文件上传下载修改
@Document(collection = DataBaseConst.KNOWLEDGE_DATABASE_NAME)
public class Knowledge extends BaseModel {
    private String name; //知识点名称
    private String file; //知识点文件存储路径
    private String grade; //年级
    private String subject; //学科
    private String description; //描述
    private String contributor; //上传人
    private String uploadTime; //上传日期

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public void updateUploadTime(){
        this.uploadTime = DateUtils.getCurrentTime();
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    public String getName() {
        return name;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public String getFile() {
        return file;
    }

    public String getDescription() {
        return description;
    }

    public String getGrade() {
        return grade;
    }

    public String getSubject() {
        return subject;
    }

    public String getContributor() {
        return contributor;
    }

}

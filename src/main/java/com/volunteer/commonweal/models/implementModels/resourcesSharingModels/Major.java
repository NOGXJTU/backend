package com.volunteer.commonweal.models.implementModels.resourcesSharingModels;

import com.volunteer.commonweal.common.DateUtils;
import com.volunteer.commonweal.common.GzipUtils;
import com.volunteer.commonweal.constants.DataBaseConst;
import com.volunteer.commonweal.constants.ModelDataConst;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

//学科介绍模型
@Document(collection = DataBaseConst.MAJOR_DATABASE_NAME)
public class Major {
    @Indexed
    private String name;//学科所属学校
    private String type; //学校类别
    @Indexed
    private String major; //专业名称
    private byte[] content; //介绍内容
    private String uploadTime; //上传日期

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void updateUploadTime(){
        this.uploadTime = DateUtils.getCurrentTime();
    }

    public String getMajor() {
        return major;
    }

    public String getContent() {
        return GzipUtils.uncompress(this.content, ModelDataConst.DEFAULT_CODING_FORMAT);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public void setContent(String content) {
        this.content = GzipUtils.compress(content, ModelDataConst.DEFAULT_CODING_FORMAT);
    }

    public void setMajor(String major) {
        this.major = major;
    }
}

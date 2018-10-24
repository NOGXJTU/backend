package com.volunteer.commonweal.models.implementModels.resourcesSharingModels;

import com.volunteer.commonweal.common.DateUtils;
import com.volunteer.commonweal.common.GzipUtils;
import com.volunteer.commonweal.constants.DataBaseConst;
import com.volunteer.commonweal.constants.ModelDataConst;
import com.volunteer.commonweal.models.BaseModel;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

//大学介绍模型
@Document(collection = DataBaseConst.COLLEGE_DATABASE_NAME)
public class College extends BaseModel {
    @Indexed(unique = true)
    private String name; //学校名字
    private String type; //学校类别
    private byte[] content; //介绍内容
    private String uploadTime; //上传日期

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }
    public void updateUploadTime(){
        this.uploadTime = DateUtils.getCurrentTime();
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = GzipUtils.compress(content, ModelDataConst.DEFAULT_CODING_FORMAT);
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return GzipUtils.uncompress(this.content, ModelDataConst.DEFAULT_CODING_FORMAT);
    }

    public String getType() {
        return type;
    }
}

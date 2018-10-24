package com.volunteer.commonweal.models.implementModels.resourcesSharingModels;

import com.volunteer.commonweal.common.DateUtils;
import com.volunteer.commonweal.common.GzipUtils;
import com.volunteer.commonweal.constants.DataBaseConst;
import com.volunteer.commonweal.constants.ModelDataConst;
import org.springframework.data.mongodb.core.mapping.Document;

//学习经历经验模型
@Document(collection = DataBaseConst.EXPERIENCE_DATABASE_NAME)
public class Experience {
    private String title; //学习经历经验题目
    private byte[] content; //学习经历经验内容
    private String contributor; //学习经历经验分享者
    private String uploadTime; //学习经历经验提交日期

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public void setContent(String content) {
        this.content = GzipUtils.compress(content, ModelDataConst.DEFAULT_CODING_FORMAT);
    }
    public void updateUploadTime(){
        this.uploadTime = DateUtils.getCurrentTime();
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public String getContributor() {
        return contributor;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return GzipUtils.uncompress(this.content, ModelDataConst.DEFAULT_CODING_FORMAT);
    }
}

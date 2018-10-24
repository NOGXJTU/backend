package com.volunteer.commonweal.models.ResponseModels.ManagementModel;


import com.volunteer.commonweal.models.implementModels.resourcesSharingModels.Question;

import java.util.List;

public class QuestionConfigData {
    public int total;
    public int perPage;
    public int page;
    public List<Question> data;
}

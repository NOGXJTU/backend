package com.volunteer.commonweal.models.ResponseModels.ManagementModel;

import com.volunteer.commonweal.models.implementModels.resourcesSharingModels.Knowledge;

import java.util.List;

public class KnowledgeConfigData {
    public int total;
    public int perPage;
    public int page;
    public List<Knowledge> data;
}

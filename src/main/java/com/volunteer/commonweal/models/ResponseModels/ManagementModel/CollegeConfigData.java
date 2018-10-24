package com.volunteer.commonweal.models.ResponseModels.ManagementModel;

import com.volunteer.commonweal.models.implementModels.resourcesSharingModels.College;

import java.util.List;

public class CollegeConfigData {
    public int total;
    public int perPage;
    public int page;
    public List<College> data;
}

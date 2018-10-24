package com.volunteer.commonweal.models;


import org.springframework.data.annotation.Id;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseModel {
    private String id;
    private Map<String, Object> meta = new HashMap<>();

    public void setId(String id) {
        this.id = id;
    }

    public void setMeta(Map<String, Object> meta) {
        this.meta = meta;
    }
    @Id
    public String getId() {
        return id;
    }

    public Map<String, Object> getMeta() {
        return meta;
    }
//    可以选择重写equal方法
}

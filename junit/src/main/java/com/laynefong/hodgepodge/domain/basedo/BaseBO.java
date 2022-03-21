package com.laynefong.hodgepodge.domain.basedo;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;


public class BaseBO implements Serializable {

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}

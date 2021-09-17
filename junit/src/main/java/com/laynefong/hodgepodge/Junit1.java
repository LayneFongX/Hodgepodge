package com.laynefong.hodgepodge;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Junit1 {

    public static void main(String[] args) {
        List<String> friendsDataCacheList = new ArrayList<>();
        friendsDataCacheList.add(null);
        friendsDataCacheList.add(null);
        // friendsDataCacheList.remove(null);
        List<String> collect = friendsDataCacheList.stream().filter(Objects::nonNull).collect(Collectors.toList());
        System.out.println(JSONObject.toJSONString(collect));
    }

}

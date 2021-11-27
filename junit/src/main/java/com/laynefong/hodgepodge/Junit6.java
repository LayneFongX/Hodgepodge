package com.laynefong.hodgepodge;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.laynefong.hodgepodge.domain.SchneiderIhcDeviceVO;
import com.laynefong.hodgepodge.domain.SchneiderIhcSubDevicesConfigBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Junit6 {

    public static void main(String[] args) {
        String subDeviceIds = "[\"eb3310c56f8bccf01c5khv\",\"eb03f1a571b02a67c0vwu9\",\"eb73b8458bfd6efa2dwo9p\"]";

        List<String> subDevicesIdList = JSONObject.parseObject(subDeviceIds,new TypeReference<>(){});
        System.out.println(subDevicesIdList);

        String cacheValue = "[{\"subDeviceId\":\"eba1ea49a4b3b13c11c4mv\",\"belongHomeId\":\"49483608\",\"selected\":false},{\"subDeviceId\":\"ebccca920927b0e60ajz09\",\"belongHomeId\":\"49483608\",\"selected\":false},{\"subDeviceId\":\"ebce4924482000adcdjsqj\",\"belongHomeId\":\"49483608\",\"selected\":false},{\"subDeviceId\":\"eb3310c56f8bccf01c5khv\",\"belongHomeId\":\"49483608\",\"selected\":false},{\"subDeviceId\":\"eb03f1a571b02a67c0vwu9\",\"belongHomeId\":\"49483608\",\"selected\":false},{\"subDeviceId\":\"eb73b8458bfd6efa2dwo9p\",\"belongHomeId\":\"49483608\",\"selected\":false}]";

        JSONArray cacheValueArray = JSONObject.parseArray(cacheValue);
        for (Object cacheValueObj : cacheValueArray) {
            JSONObject cacheValueJson = (JSONObject) cacheValueObj;

            String subDeviceId = cacheValueJson.getString("subDeviceId");
            if (subDevicesIdList.contains(subDeviceId)) {
                cacheValueJson.put("selected", Boolean.TRUE);
                cacheValueJson.put("belongHomeId", "1248");
            }
        }

        System.out.println(JSONObject.toJSONString(cacheValueArray));

    }

}

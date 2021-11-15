package com.laynefong.hodgepodge;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.laynefong.hodgepodge.domain.SchneiderIhcDeviceVO;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Junit4 {

    public static void main(String[] args) {
        String _ihcDeviceBOListStr = "[{\"id\":\"eb174227dca54f738dvbuu\",\"deviceName\":\"Lampeudtag\",\"roomName\":\"Bryggers\"}]";
        JSONArray ihcDeviceBOJSONArray = JSONObject.parseArray(_ihcDeviceBOListStr);

        List<SchneiderIhcDeviceVO> ihcSubDevicesList = JSONObject.parseObject(JSONObject.toJSONString(ihcDeviceBOJSONArray), new TypeReference<>() {
        });
        List<String> ihcSubDevicesIdList = ihcSubDevicesList.stream().map(SchneiderIhcDeviceVO::getId).collect(Collectors.toList());
        System.out.println(ihcSubDevicesList);
    }
}

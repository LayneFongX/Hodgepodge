package com.laynefong.hodgepodge;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.laynefong.hodgepodge.domain.SchneiderIhcDeviceVO;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Junit4 {

    public static void main(String[] args) {
        // String _ihcDeviceBOListStr = "[{\"id\":\"eb174227dca54f738dvbuu\",\"deviceName\":\"Lampeudtag\",\"roomName\":\"Bryggers\"}]";
        // JSONArray ihcDeviceBOJSONArray = JSONObject.parseArray(_ihcDeviceBOListStr);
        //
        // List<SchneiderIhcDeviceVO> ihcSubDevicesList = JSONObject.parseObject(JSONObject.toJSONString(ihcDeviceBOJSONArray), new TypeReference<>() {
        // });
        // List<String> ihcSubDevicesIdList = ihcSubDevicesList.stream().map(SchneiderIhcDeviceVO::getId).collect(Collectors.toList());
        // System.out.println(ihcSubDevicesList);
        // String _a = "rack 1-6";
        // System.out.println(_a.substring(_a.lastIndexOf(" ") + 1));

        List<String> devicesNameList = new ArrayList<>();

        String _a = "rack 3";
        String _b = "rack 1";
        String _c = "rack";
        String _d = "rack 4";

        devicesNameList.add(_b);
        devicesNameList.add(_a);
        devicesNameList.add(_c);
        devicesNameList.add(_d);

        Collections.sort(devicesNameList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        System.out.println(devicesNameList);
    }
}

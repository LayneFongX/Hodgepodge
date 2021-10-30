package com.laynefong.hodgepodge;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.laynefong.hodgepodge.domain.PointVO;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class Junit3 {

    private static List<String> getVerifyParamList(String paramName, Map<String, Object> argsMap) {
        return List.of(argsMap.get(paramName).toString().split(","));
    }

    public static void main(String[] args) {
        String _a = "1";
        List<String> split = List.of(_a.split(","));
        System.out.println(split.getClass().getName());

        List<String> strings = Arrays.asList(_a.split(","));
        System.out.println(strings.getClass().getName());

    }
}

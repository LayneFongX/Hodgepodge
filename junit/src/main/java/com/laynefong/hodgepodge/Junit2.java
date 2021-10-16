package com.laynefong.hodgepodge;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

public class Junit2 {

    public static void main(String[] args) {
        String _a = "{\n" +
                "  \"count\": 0,\n" +
                "  \"ct\": 1632455569840,\n" +
                "  \"data\": {\n" +
                "    \"executorProperty\": \"{\\\"actionDisplayNew\\\":\\\"{\\\\\\\"102\\\\\\\":[\\\\\\\"wiser_boost_name\\\\\\\",\\\\\\\"wiser_boost_30_mins\\\\\\\"]}\\\",\\\"102\\\":\\\"30\\\",\\\"resType\\\":\\\"2\\\",\\\"bizCode\\\":\\\"schneider_wkf_boost\\\",\\\"actionExecutor\\\":\\\"deviceGroupDpIssue\\\",\\\"entityId\\\":\\\"6cc9462add466cd4e8bfcp\\\",\\\"pid\\\":\\\"a6xa00qn\\\",\\\"resId\\\":\\\"6682213\\\",\\\"value\\\":\\\"30\\\"}\",\n" +
                "    \"bizCode\": \"schneider_wkf_boost\",\n" +
                "    \"entityId\": \"6682213\"\n" +
                "  },\n" +
                "  \"rt\": 2\n" +
                "}";
        JSONObject kafkaMqData = JSONObject.parseObject(_a);
        JSONObject linkageActionJson = kafkaMqData.getJSONObject("data");
        System.out.println(linkageActionJson);

        JSONObject executorPropertyJson = linkageActionJson.getJSONObject("executorProperty");
        System.out.println(executorPropertyJson);

        String bizCode = executorPropertyJson.getString("bizCode");
        System.out.println(bizCode);

        String resId = executorPropertyJson.getString("resId");
        Integer resType = executorPropertyJson.getInteger("resType");
        String value = executorPropertyJson.getString("value");
        System.out.println(resId);
        System.out.println(resType);
        System.out.println(value);


    }
}

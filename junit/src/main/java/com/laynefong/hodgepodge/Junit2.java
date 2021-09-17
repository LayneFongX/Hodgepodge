package com.laynefong.hodgepodge;

import com.alibaba.fastjson.JSONObject;

public class Junit2 {

    public static void main(String[] args) {
        String _a = "{\"uid\":\"europa\",\"matchType\":1,\"ruleType\":5,\"name\":\"施耐德温控阀设备当前温度dp点转发\",\"requestSource\":\"europa\",\"conditions\":[{\"entitySubIds\":\"102\",\"entityType\":1,\"condType\":2,\"entityId\":\"a6xa00qn\",\"expr\":[[\"$dp102\"]]},{\"entitySubIds\":\"102\",\"entityType\":1,\"condType\":2,\"entityId\":\"wpjh5c75\",\"expr\":[[\"$dp102\"]]},{\"entitySubIds\":\"102\",\"entityType\":1,\"condType\":2,\"entityId\":\"7wq94vhy\",\"expr\":[[\"$dp102\"]]},{\"entitySubIds\":\"102\",\"entityType\":1,\"condType\":2,\"entityId\":\"orey2coh\",\"expr\":[[\"$dp102\"]]}],\"actions\":[{\"actionStrategy\":\"repeat\",\"executorProperty\":{\"bizCode\":\"schneiderWkfRealTemp\",\"isString\":true},\"actionExecutor\":\"dpForward\",\"entityId\":\"jupiter_dp_forward_europa\"}],\"enabled\":true}";
        System.out.println(JSONObject.parse(_a));
    }
}

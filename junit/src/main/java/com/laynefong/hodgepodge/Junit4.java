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
        String _ihcDeviceBOListStr = "[{\"ability\":0,\"accessType\":3,\"activeTime\":1636709177,\"customName\":\"\",\"id\":\"eb174227dca54f738dvbuu\",\"mac\":\"ihc_0x6054\",\"name\":\"IHC-Relay\",\"ownerId\":\"49483608\",\"productId\":\"krblkhhp\",\"sub\":true,\"timeZone\":\"+08:00\",\"timeZoneId\":\"Asia/Shanghai\",\"uid\":\"az1631756712981FU5XV\",\"uuid\":\"eb174227dca54f738dvbuu\"},{\"ability\":0,\"accessType\":3,\"activeTime\":1636709179,\"customName\":\"\",\"id\":\"eb0cf6b02c1d04ec170zmy\",\"mac\":\"ihc_0x5653\",\"name\":\"IHC-Temperature/Humidity Sensor\",\"ownerId\":\"49483608\",\"productId\":\"hlqy1n3p\",\"sub\":true,\"timeZone\":\"+08:00\",\"timeZoneId\":\"Asia/Shanghai\",\"uid\":\"az1631756712981FU5XV\",\"uuid\":\"eb0cf6b02c1d04ec170zmy\"},{\"ability\":0,\"accessType\":3,\"activeTime\":1636709181,\"customName\":\"\",\"id\":\"eb6bfee1ee3eff2bd9ctyc\",\"mac\":\"ihc_0x6b54\",\"name\":\"IHC-Dimmer\",\"ownerId\":\"49483608\",\"productId\":\"a19i5pcm\",\"sub\":true,\"timeZone\":\"+08:00\",\"timeZoneId\":\"Asia/Shanghai\",\"uid\":\"az1631756712981FU5XV\",\"uuid\":\"eb6bfee1ee3eff2bd9ctyc\"},{\"ability\":0,\"accessType\":3,\"activeTime\":1636709182,\"customName\":\"\",\"id\":\"eb6a9efc2b430b987bceaw\",\"mac\":\"ihc_0x6654\",\"name\":\"IHC- Wireless Freelocate\",\"ownerId\":\"49483608\",\"productId\":\"9nb2ss4z\",\"sub\":true,\"timeZone\":\"+08:00\",\"timeZoneId\":\"Asia/Shanghai\",\"uid\":\"az1631756712981FU5XV\",\"uuid\":\"eb6a9efc2b430b987bceaw\"},{\"ability\":0,\"accessType\":3,\"activeTime\":1636709184,\"customName\":\"\",\"id\":\"ebae6f1d0a2a5c5ddda10m\",\"mac\":\"ihc_0x5e53\",\"name\":\"IHC-Window/Door Sensor\",\"ownerId\":\"49483608\",\"productId\":\"ppfq1jjd\",\"sub\":true,\"timeZone\":\"+08:00\",\"timeZoneId\":\"Asia/Shanghai\",\"uid\":\"az1631756712981FU5XV\",\"uuid\":\"ebae6f1d0a2a5c5ddda10m\"},{\"ability\":0,\"accessType\":3,\"activeTime\":1636709186,\"customName\":\"\",\"id\":\"eb0a249ccdfbc2ea34vjiw\",\"mac\":\"ihc_0x5153\",\"name\":\"IHC-Wired Freelocate\",\"ownerId\":\"49483608\",\"productId\":\"sxynblhb\",\"sub\":true,\"timeZone\":\"+08:00\",\"timeZoneId\":\"Asia/Shanghai\",\"uid\":\"az1631756712981FU5XV\",\"uuid\":\"eb0a249ccdfbc2ea34vjiw\"}]";
        JSONArray ihcDeviceBOJSONArray = JSONObject.parseArray(_ihcDeviceBOListStr);

        List<SchneiderIhcDeviceVO> ichSubDevices = JSONObject.parseObject(JSONObject.toJSONString(ihcDeviceBOJSONArray), new TypeReference<>() {
        });
        System.out.println(ichSubDevices);
    }
}

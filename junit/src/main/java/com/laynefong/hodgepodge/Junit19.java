package com.laynefong.hodgepodge;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.laynefong.hodgepodge.domain.SolutionDeviceDpStatBaseEntityVO;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Banchao
 * @Date 2022/5/9 18:39
 */
public class Junit19 {

    public static void main(String[] args) {
        String _a = "[{\n" +
                "\t\"devId\": \"bf32e85ec876bdaeb3xprc\",\n" +
                "\t\"dpId\": \"114\",\n" +
                "\t\"dt\": \"2022050900\",\n" +
                "\t\"rangeValue\": 115,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf32e85ec876bdaeb3xprc\",\n" +
                "\t\"dpId\": \"114\",\n" +
                "\t\"dt\": \"2022050901\",\n" +
                "\t\"rangeValue\": 475,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf32e85ec876bdaeb3xprc\",\n" +
                "\t\"dpId\": \"114\",\n" +
                "\t\"dt\": \"2022050906\",\n" +
                "\t\"rangeValue\": 602,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf32e85ec876bdaeb3xprc\",\n" +
                "\t\"dpId\": \"114\",\n" +
                "\t\"dt\": \"2022050810\",\n" +
                "\t\"rangeValue\": 797,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf32e85ec876bdaeb3xprc\",\n" +
                "\t\"dpId\": \"114\",\n" +
                "\t\"dt\": \"2022050812\",\n" +
                "\t\"rangeValue\": 811,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050712\",\n" +
                "\t\"rangeValue\": 8,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050910\",\n" +
                "\t\"rangeValue\": 43,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050814\",\n" +
                "\t\"rangeValue\": 65,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050905\",\n" +
                "\t\"rangeValue\": 69,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050807\",\n" +
                "\t\"rangeValue\": 81,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050709\",\n" +
                "\t\"rangeValue\": 82,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050900\",\n" +
                "\t\"rangeValue\": 82,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050813\",\n" +
                "\t\"rangeValue\": 83,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050804\",\n" +
                "\t\"rangeValue\": 85,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050816\",\n" +
                "\t\"rangeValue\": 85,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050818\",\n" +
                "\t\"rangeValue\": 85,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050823\",\n" +
                "\t\"rangeValue\": 86,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050700\",\n" +
                "\t\"rangeValue\": 88,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050806\",\n" +
                "\t\"rangeValue\": 88,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050711\",\n" +
                "\t\"rangeValue\": 89,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050819\",\n" +
                "\t\"rangeValue\": 89,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050707\",\n" +
                "\t\"rangeValue\": 93,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050708\",\n" +
                "\t\"rangeValue\": 95,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050706\",\n" +
                "\t\"rangeValue\": 96,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050904\",\n" +
                "\t\"rangeValue\": 96,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050817\",\n" +
                "\t\"rangeValue\": 96,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050704\",\n" +
                "\t\"rangeValue\": 100,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050710\",\n" +
                "\t\"rangeValue\": 105,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050800\",\n" +
                "\t\"rangeValue\": 108,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050815\",\n" +
                "\t\"rangeValue\": 108,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050808\",\n" +
                "\t\"rangeValue\": 109,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050705\",\n" +
                "\t\"rangeValue\": 111,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050909\",\n" +
                "\t\"rangeValue\": 114,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050911\",\n" +
                "\t\"rangeValue\": 116,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050805\",\n" +
                "\t\"rangeValue\": 117,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050822\",\n" +
                "\t\"rangeValue\": 124,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050820\",\n" +
                "\t\"rangeValue\": 168,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050908\",\n" +
                "\t\"rangeValue\": 175,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050907\",\n" +
                "\t\"rangeValue\": 241,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050811\",\n" +
                "\t\"rangeValue\": 251,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050809\",\n" +
                "\t\"rangeValue\": 273,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050821\",\n" +
                "\t\"rangeValue\": 347,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050906\",\n" +
                "\t\"rangeValue\": 821,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050810\",\n" +
                "\t\"rangeValue\": 876,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050812\",\n" +
                "\t\"rangeValue\": 960,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050803\",\n" +
                "\t\"rangeValue\": 3114,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050703\",\n" +
                "\t\"rangeValue\": 3136,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050903\",\n" +
                "\t\"rangeValue\": 3214,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050801\",\n" +
                "\t\"rangeValue\": 8170,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050701\",\n" +
                "\t\"rangeValue\": 8255,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050901\",\n" +
                "\t\"rangeValue\": 8588,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050902\",\n" +
                "\t\"rangeValue\": 9868,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050802\",\n" +
                "\t\"rangeValue\": 10192,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf2bbe11d2967a262ccqok\",\n" +
                "\t\"dpId\": \"115\",\n" +
                "\t\"dt\": \"2022050702\",\n" +
                "\t\"rangeValue\": 11736,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf0d45d5f63deb6e30aj1v\",\n" +
                "\t\"dpId\": \"114\",\n" +
                "\t\"dt\": \"2022050803\",\n" +
                "\t\"rangeValue\": 3029,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf0d45d5f63deb6e30aj1v\",\n" +
                "\t\"dpId\": \"114\",\n" +
                "\t\"dt\": \"2022050903\",\n" +
                "\t\"rangeValue\": 3097,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf0d45d5f63deb6e30aj1v\",\n" +
                "\t\"dpId\": \"114\",\n" +
                "\t\"dt\": \"2022050703\",\n" +
                "\t\"rangeValue\": 3123,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf0d45d5f63deb6e30aj1v\",\n" +
                "\t\"dpId\": \"114\",\n" +
                "\t\"dt\": \"2022050801\",\n" +
                "\t\"rangeValue\": 8048,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf0d45d5f63deb6e30aj1v\",\n" +
                "\t\"dpId\": \"114\",\n" +
                "\t\"dt\": \"2022050901\",\n" +
                "\t\"rangeValue\": 8106,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf0d45d5f63deb6e30aj1v\",\n" +
                "\t\"dpId\": \"114\",\n" +
                "\t\"dt\": \"2022050701\",\n" +
                "\t\"rangeValue\": 8187,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf0d45d5f63deb6e30aj1v\",\n" +
                "\t\"dpId\": \"114\",\n" +
                "\t\"dt\": \"2022050902\",\n" +
                "\t\"rangeValue\": 11563,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf0d45d5f63deb6e30aj1v\",\n" +
                "\t\"dpId\": \"114\",\n" +
                "\t\"dt\": \"2022050802\",\n" +
                "\t\"rangeValue\": 11566,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf0d45d5f63deb6e30aj1v\",\n" +
                "\t\"dpId\": \"114\",\n" +
                "\t\"dt\": \"2022050702\",\n" +
                "\t\"rangeValue\": 11737,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf1b41d36e30836d4fkeoz\",\n" +
                "\t\"dpId\": \"114\",\n" +
                "\t\"dt\": \"2022050808\",\n" +
                "\t\"rangeValue\": 0,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf1b41d36e30836d4fkeoz\",\n" +
                "\t\"dpId\": \"114\",\n" +
                "\t\"dt\": \"2022050810\",\n" +
                "\t\"rangeValue\": 350,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bf1b41d36e30836d4fkeoz\",\n" +
                "\t\"dpId\": \"114\",\n" +
                "\t\"dt\": \"2022050809\",\n" +
                "\t\"rangeValue\": 431,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bfb9dfd66e1178fcfa6xw1\",\n" +
                "\t\"dpId\": \"114\",\n" +
                "\t\"dt\": \"2022050907\",\n" +
                "\t\"rangeValue\": 51,\n" +
                "\t\"timeType\": 0\n" +
                "}, {\n" +
                "\t\"devId\": \"bfb9dfd66e1178fcfa6xw1\",\n" +
                "\t\"dpId\": \"114\",\n" +
                "\t\"dt\": \"2022050821\",\n" +
                "\t\"rangeValue\": 117,\n" +
                "\t\"timeType\": 0\n" +
                "}]";
        List<SolutionDeviceDpStatBaseEntityVO> list = JSONObject.parseObject(_a, new TypeReference<>() {
        });
        List<SolutionDeviceDpStatBaseEntityVO> c1 =
                list.stream().filter(x -> x.getDevId().equals("bf2bbe11d2967a262ccqok")).collect(Collectors.toList());
        Collections.sort(c1, new Comparator<SolutionDeviceDpStatBaseEntityVO>() {
            @Override
            public int compare(SolutionDeviceDpStatBaseEntityVO o1, SolutionDeviceDpStatBaseEntityVO o2) {
                return o1.getDt().compareTo(o2.getDt());
            }
        });
        System.out.println(JSONObject.toJSONString(c1));
    }
}
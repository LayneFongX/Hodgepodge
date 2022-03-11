package com.laynefong.hodgepodge;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.laynefong.hodgepodge.domain.config.MultiLanguagePreConfigProductsVO;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author Banchao
 * @Date 2022/3/11 11:59
 */
public class Junit12 {

    public static void main(String[] args) {
        String cacheValue =
                "{\"智能控制器\":\"q6rw2fwke5uqwxmm\",\"ICONIC Silab 2A Switch\":\"c9tlexcw\",\"Single Smart Outlet\":\"iltpjpto\",\"TYZXl鹊起 门窗磁感应器\":\"bqwqlhdc\",\"Iconic Wiser 2A Switch\":\"qjodrtzy\",\"Smart Plug (WiFi)\":\"ib4aqphtpizhvnaj\",\"零火触摸三键开关\":\"oh3tmf4m\",\"新风管家（瑞博恩常规）\":\"b0kojifhlrk3ptfw\",\"HiFi2\":\"rxpihnnv54elpitv\",\"TYZXl鹊起 SOS紧急按钮\":\"ux0evj9k\",\"2G Curtain - Cuadro H\":\"ysqxplgj\",\"Smoke Alarm\":\"trhbfkkh\",\"IR Convertor\":\"koenetf0\",\"Outdoor IP Camera\":\"xt1qr2ugq1kptnbq\",\"GYlight0525测试\":\"lxzqpjbh15jdjwxh\",\"1111\":\"atvz4ay9s299y3rm\",\"NH Push Button Dimmer (2/3-wire)\":\"go8bnkia\",\"IP Camera\":\"jng7tpfobpbgylwv\",\"4路场景+开关\":\"ryzzhuhs\",\"四路场景开关\":\"apjrpmcu\",\"灯泡\":\"oejvmuqbh45ac0mr\",\"6K Freelocate\":\"shrfjncj\",\"System Freelocate\":\"yj6hqvry\",\"KA测试的破自行车\":\"ypolfrz6\",\"Elko Smart Plug\":\"p9bzcbzf\",\"Temperature/Humidity Sensor\":\"eao4rrrd\",\"电动轮椅\":\"weavurz7\",\"2G Curtain Switch\":\"5pp4cb6m\",\"Window/Door Sensor\":\"qbn0v6sx\",\"Siren\":\"jy0gmh58\",\"Smoke Alarm (DC)\":\"petebj51\",\"Smart Socket\":\"qhg5mzro\",\"Merten 1G Shutter\":\"csyu9cag\",\"平衡车\":\"okhv082a\",\"1\":\"chspet7v\",\"电动车\":\"cqwiwttl\",\"滑板车\":\"l0gbvkav\",\"米博烹饪机\":\"nv994lszan6hmiay\",\"开关\":\"k9sfrlql\",\"4键调光\":\"dwylvuws\",\"GYlight\":\"tlzbj86zx2ulsohm\",\"TYZX|豪恩 水浸报警器\":\"b9lu4d2x\",\"Out Camera E97VR72（Schneider）\":\"cpq6klqah1as0sec\",\"YL-采暖温控器\":\"jktefxp3alh7u1sk\"}";
        Map<String, String> productName2PidMap = JSONObject.parseObject(cacheValue, new TypeReference<>() {
        });

        // 排序
        LinkedHashMap<String, String> sortProductName2PidMap = productName2PidMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (key1, key2) -> key1, LinkedHashMap::new));

        // 转为list
        List<MultiLanguagePreConfigProductsVO> multiLanguagePreConfigProductList = sortProductName2PidMap.entrySet().stream()
                .map(x -> MultiLanguagePreConfigProductsVO.builder().productName(x.getKey()).pid(x.getValue()).build())
                .collect(Collectors.toList());

        List<String> dbSelectProductsIdList = new ArrayList<>();
        dbSelectProductsIdList.add("sdy3m5xz");
        dbSelectProductsIdList.add("ajauadjx");
        dbSelectProductsIdList.add("wdu8skfpbsgzptyt");
        dbSelectProductsIdList.add("orey2coh");
        dbSelectProductsIdList.add("z8ntnhnm");
        dbSelectProductsIdList.add("e9zia2f8");
        dbSelectProductsIdList.add("mkzpabvl");
        dbSelectProductsIdList.add("uzpsufgy");
        dbSelectProductsIdList.add("a9tu1kin");
        dbSelectProductsIdList.add("okhv082a");
        dbSelectProductsIdList.add("k9sfrlql");
        dbSelectProductsIdList.add("rxpihnnv54elpitv");
        dbSelectProductsIdList.add("c9tlexcw");
        dbSelectProductsIdList.add("l0gbvkav");
        removeDirtyProductIds(multiLanguagePreConfigProductList, dbSelectProductsIdList);
        System.out.println(dbSelectProductsIdList);
    }

    private static void removeDirtyProductIds(List<MultiLanguagePreConfigProductsVO> multiLanguagePreConfigProductList,
                                              List<String> dbSelectProductsIdList) {
        List<String> preConfigProductIdList =
                multiLanguagePreConfigProductList.stream().map(MultiLanguagePreConfigProductsVO::getPid).collect(Collectors.toList());
        Iterator<String> iterator = dbSelectProductsIdList.iterator();
        while (iterator.hasNext()) {
            String selectedProductId = iterator.next();
            if (preConfigProductIdList.contains(selectedProductId)) {
                continue;
            }
            iterator.remove();
        }

    }

}

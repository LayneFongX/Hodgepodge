package com.laynefong.hodgepodge;

import com.google.common.collect.Lists;

import java.util.*;

public class Junit7 {

    public static void main(String[] args) {
        Set<String> itemCodesSet = new HashSet<>(
                Arrays.asList("AYkxJox7JgB1x6pN_name", "AYkxJox7JgB1x6pN_content", "ObD17yK2NuM9cAfh_name", "ObD17yK2NuM9cAfh_content",
                        "78154cAfh_name", "78154cAfh_content"));
        Map<String, Set<String>> itemCodesMap = new HashMap<>();
        // 获取bizId
        itemCodesSet.forEach(itemCode -> {
            String[] codeInfo = itemCode.split("_");
            String bizId = codeInfo[0];
            String code = codeInfo[1];
            itemCodesMap.putIfAbsent(bizId, new HashSet<>());
            itemCodesMap.get(bizId).add(code);
        });

        List<List<String>> itemCodesParts =
                Lists.partition(new ArrayList<>(itemCodesMap.keySet()), 2);
        itemCodesParts.forEach(partList -> {
            // 产品名称  key为词条名称 value为对应的多语言的值
            for (String itemBizId : partList) {
                Set<String> codes = itemCodesMap.get(itemBizId);
                System.out.println(itemBizId + "--------" + codes);
            }
        });

        System.out.println(itemCodesMap);
    }
}

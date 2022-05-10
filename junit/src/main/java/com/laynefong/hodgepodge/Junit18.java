package com.laynefong.hodgepodge;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.laynefong.hodgepodge.domain.SolutionDeviceDpStatBaseEntityVO;
import com.laynefong.hodgepodge.domain.SolutionDeviceDpStatListVO;
import com.laynefong.hodgepodge.domain.SolutionDeviceDpStatOverViewVO;
import com.laynefong.hodgepodge.domain.SolutionTariffPlanEntity;
import com.laynefong.hodgepodge.enums.DashBoardTypeEnum;
import com.laynefong.hodgepodge.enums.StatTypeEnum;
import com.laynefong.hodgepodge.enums.TimeTypeEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author Banchao
 * @Date 2022/5/7 11:33
 */
public class Junit18 {

    //电表耗电的dpId
    private static final String POWERTAG_PID_DPID_CONSUMPTION = "114";

    //电表发电的dpId
    private static final String POWERTAG_PID_DPID_PRODUCT = "115";

    //电表label的dpId
    private static final String POWERTAG_PID_DPID_LABEL = "109";

    //发电还是耗电 {"range":["Consumption","Production"],"type":"enum"}
    private static final String POWERTAG_PID_DPID_Metering_Type = "116";
    //116对应的值
    private static final String POWERTAG_PID_DPID_Metering_Type_Production = "Production";

    private static final String POWERTAG_PID_DPID_Metering_Type_Consumption = "Consumption";

    public static void main(String[] args) {
        SolutionDeviceDpStatListVO result =
                statListByDpNew("", "bf2bbe11d2967a262ccqok", "18391695", 1, DashBoardTypeEnum.TOTAL, StatTypeEnum.USAGE,
                        TimeTypeEnum.DAY, "20220506", Arrays.asList("nccooqhf", "of1wzxhd", "84db4ryf", "gvqbxz9m", "wdu8skfpbsgzptyt"));
        System.out.println(JSONObject.toJSONString(result));
    }

    private static SolutionDeviceDpStatListVO statListByDpNew(String uid, String mainDevId, String resId, Integer resType,
                                                              DashBoardTypeEnum dashBoardType, StatTypeEnum statType, TimeTypeEnum timeType,
                                                              String time, List<String> schneiderPowerTagOldPid) {

        SolutionTariffPlanEntity tariffPlanEntity = getSolutionTariffPlanEntity();

        // 查询数据
        List<SolutionDeviceDpStatBaseEntityVO> daysDeviceDpStat = getSolutionDeviceDpStatBaseEntityList();

        //3.0 获取devId对应label值
        Map<String, JSONObject> dataPointBOMap = getDataPointBOMap();
        //耗电、发电类型
        final Map<String, String> typeDpIdMap = dataPointBOMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, it -> {
            if (it.getValue() != null) {
                String label = it.getValue().getString(POWERTAG_PID_DPID_Metering_Type);
                return StringUtils.defaultString(label, "");
            }
            return "";
        }));

        //label值
        final Map<String, String> labelDpIdMap = dataPointBOMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, it -> {
            if (it.getValue() != null) {
                String label = it.getValue().getString(POWERTAG_PID_DPID_LABEL);
                //进一步判断
                if (dashBoardType == DashBoardTypeEnum.CONSUMPTION) {
                    if (StringUtils.equals(POWERTAG_PID_DPID_Metering_Type_Production, typeDpIdMap.get(it.getKey()))) {
                        return "None";
                    }
                }
                if (dashBoardType == DashBoardTypeEnum.PRODUCT) {
                    if (StringUtils.equals(POWERTAG_PID_DPID_Metering_Type_Consumption, typeDpIdMap.get(it.getKey()))) {
                        return "None";
                    }
                }
                if (dashBoardType == DashBoardTypeEnum.TOTAL) {
                    if (StringUtils.equals(POWERTAG_PID_DPID_Metering_Type_Consumption, typeDpIdMap.get(it.getKey())) ||
                            StringUtils.equals(POWERTAG_PID_DPID_Metering_Type_Production, typeDpIdMap.get(it.getKey()))) {
                        return "None";
                    }
                }
                return StringUtils.defaultString(label, "None");
            }
            return "None";
        }));
        System.out.println(labelDpIdMap);
        //4.1 组装数据
        SolutionDeviceDpStatListVO dpStatListVO = new SolutionDeviceDpStatListVO();
        dpStatListVO.setDashBoardType(dashBoardType.getCode());
        dpStatListVO.setStatType(statType.getCode());
        SolutionDeviceDpStatOverViewVO overview = new SolutionDeviceDpStatOverViewVO();
        dpStatListVO.setOverview(overview);
        if (tariffPlanEntity != null) {
            dpStatListVO.setCurrency(tariffPlanEntity.getCurrency());
        }

        if (CollectionUtils.isEmpty(daysDeviceDpStat)) {
            return dpStatListVO;
        }

        //4.2 设置 overview 该展示，主电表一定是在CONSUMPTION，如果是查询PRODUCT则是其他之和
        // 因为不区分发电和耗电 因此查询overview的值为主电表的114+115
        List<SolutionDeviceDpStatBaseEntityVO> totalEntityList =
                daysDeviceDpStat.stream().filter(it -> StringUtils.equals(mainDevId, it.getDevId())).collect(Collectors.toList());
        Collections.sort(totalEntityList, new Comparator<SolutionDeviceDpStatBaseEntityVO>() {
            @Override
            public int compare(SolutionDeviceDpStatBaseEntityVO o1, SolutionDeviceDpStatBaseEntityVO o2) {
                return o1.getDt().compareTo(o2.getDt());
            }
        });
        System.out.println("total--------" + JSONObject.toJSONString(totalEntityList));
        BigDecimal total = daysDeviceDpStat.stream().filter(it -> StringUtils.equals(mainDevId, it.getDevId()))
                .map(it -> mapStatTypeBigDecimal(statType, it)).reduce(BigDecimal.ZERO, BigDecimal::add);
        overview.setTotal(total);

        List<SolutionDeviceDpStatBaseEntityVO> EntityList1 =
                daysDeviceDpStat.stream().filter(it -> StringUtils.equals("bf0d45d5f63deb6e30aj1v", it.getDevId()))
                        .sorted(Comparator.comparing(SolutionDeviceDpStatBaseEntityVO::getDt))
                        .collect(Collectors.toList());
        System.out.println("bf0d45d5f63deb6e30aj1v-----------------" + JSONObject.toJSONString(EntityList1));

        List<SolutionDeviceDpStatBaseEntityVO> EntityList2 =
                daysDeviceDpStat.stream().filter(it -> StringUtils.equals("bfb9dfd66e1178fcfa6xw1", it.getDevId()))
                        .sorted(Comparator.comparing(SolutionDeviceDpStatBaseEntityVO::getDt))
                        .collect(Collectors.toList());
        System.out.println("bfb9dfd66e1178fcfa6xw1-----------------" + JSONObject.toJSONString(EntityList2));


        List<SolutionDeviceDpStatBaseEntityVO> EntityList3 =
                daysDeviceDpStat.stream().filter(it -> StringUtils.equals("bf32e85ec876bdaeb3xprc", it.getDevId()))
                        .sorted(Comparator.comparing(SolutionDeviceDpStatBaseEntityVO::getDt))
                        .collect(Collectors.toList());
        System.out.println("bf32e85ec876bdaeb3xprc-----------------" + JSONObject.toJSONString(EntityList3));

        // List<SolutionDeviceDpStatBaseEntityVO> EntityList4 =
        //         daysDeviceDpStat.stream().filter(it -> StringUtils.equals("bf667a79abdd9a97eeralf", it.getDevId()))
        //                 .sorted(Comparator.comparing(SolutionDeviceDpStatBaseEntityVO::getDt))
        //                 .collect(Collectors.toList());
        // System.out.println("bf667a79abdd9a97eeralf-----------------" + JSONObject.toJSONString(EntityList4));
        //
        //
        // List<SolutionDeviceDpStatBaseEntityVO> EntityList5 =
        //         daysDeviceDpStat.stream().filter(it -> StringUtils.equals("bf36a097c3dfb1e3efeuup", it.getDevId()))
        //                 .sorted(Comparator.comparing(SolutionDeviceDpStatBaseEntityVO::getDt))
        //                 .collect(Collectors.toList());
        // System.out.println("bf36a097c3dfb1e3efeuup-----------------" + JSONObject.toJSONString(EntityList5));
        //
        // List<SolutionDeviceDpStatBaseEntityVO> EntityList6 =
        //         daysDeviceDpStat.stream().filter(it -> StringUtils.equals("bf8a660a94358e95cc2ba6", it.getDevId()))
        //                 .sorted(Comparator.comparing(SolutionDeviceDpStatBaseEntityVO::getDt))
        //                 .collect(Collectors.toList());
        // System.out.println("bf8a660a94358e95cc2ba6-----------------" + JSONObject.toJSONString(EntityList6));
        //
        // List<SolutionDeviceDpStatBaseEntityVO> EntityList7 =
        //         daysDeviceDpStat.stream().filter(it -> StringUtils.equals("bff2e08db799aab363s9e5", it.getDevId()))
        //                 .sorted(Comparator.comparing(SolutionDeviceDpStatBaseEntityVO::getDt))
        //                 .collect(Collectors.toList());
        // System.out.println("bff2e08db799aab363s9e5-----------------" + JSONObject.toJSONString(EntityList7));


        // 因为不区分发电和耗电 此处查询的是非主电表的设备
        Map<String, BigDecimal> labelSumMap = daysDeviceDpStat.stream()
                .filter(it -> !StringUtils.equals(mainDevId, it.getDevId()))
                .collect(Collectors.groupingBy(m -> labelDpIdMap.getOrDefault(m.getDevId(), "None"),
                        Collectors.reducing(BigDecimal.ZERO, it -> mapStatTypeBigDecimal(statType, it),
                                BigDecimal::add)));

        // other 特定值， total 减去有具体的label值
        // 因为主电表和子电表跨小时的增量不一致 所以经常出现主电表的数据小于子电表的数据 该问题是历史遗留问题 无法修改
        Map<String, BigDecimal> typeDetailMap = labelSumMap.entrySet().stream()
                .filter(it -> StringUtils.isNotEmpty(it.getKey()))
                .filter(it -> !StringUtils.equals(it.getKey(), "None"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        BigDecimal otherLabel = typeDetailMap.values().stream().reduce(total, BigDecimal::subtract);
        typeDetailMap.put("other", otherLabel);
        overview.setTypeDetail(typeDetailMap);

        //4.2 设置 timeDetails，展示普通label的值
        // 因为不区分发电和耗电 此处查询的是非主电表的设备
        Map<String, Map<String, BigDecimal>> dtLabelSumMap = daysDeviceDpStat.stream()
                .filter(it -> !StringUtils.equals(mainDevId, it.getDevId()))
                .collect(Collectors.groupingBy(SolutionDeviceDpStatBaseEntityVO::getDt,
                        Collectors.groupingBy(m -> labelDpIdMap.getOrDefault(m.getDevId(), "None"),
                                Collectors.reducing(BigDecimal.ZERO, it -> mapStatTypeBigDecimal(statType, it),
                                        BigDecimal::add))));

        //主电表的totalDtMap
        final Map<String, BigDecimal> timeDetailTotalMap = daysDeviceDpStat.stream()
                .filter(it -> StringUtils.equals(mainDevId, it.getDevId()))
                .collect(Collectors.groupingBy(SolutionDeviceDpStatBaseEntityVO::getDt,
                        Collectors.reducing(BigDecimal.ZERO, it -> mapStatTypeBigDecimal(statType, it),
                                BigDecimal::add)));

        //增加出other值，other = total - sum(label)
        Map<String, Map<String, BigDecimal>> timeDetailMap1 = new HashMap<>(timeDetailTotalMap.size());
        dtLabelSumMap.entrySet().stream()
                .filter(it -> MapUtils.isNotEmpty(it.getValue()))
                .forEach(
                        it -> {
                            String dt = it.getKey();
                            //有label的map
                            Map<String, BigDecimal> dtTypeSum = it.getValue().entrySet().stream()
                                    .filter(iit -> !StringUtils.equals(iit.getKey(), "None"))
                                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                            if (!timeDetailMap1.containsKey(dt)) {
                                timeDetailMap1.put(dt, new HashMap<>());
                            }
                            timeDetailMap1.get(dt).putAll(dtTypeSum);
                            BigDecimal otherSum = it.getValue().entrySet().stream()
                                    .filter(iit -> !StringUtils.equals(iit.getKey(), "None"))
                                    .map(Map.Entry::getValue)
                                    .reduce(timeDetailTotalMap.getOrDefault(it.getKey(), BigDecimal.ZERO),
                                            BigDecimal::subtract);
                            timeDetailMap1.get(dt).put("other", otherSum);
                            timeDetailMap1.get(dt).remove("None");
                        }
                );

        // 主电表可能存在子设备没有的dt
        timeDetailTotalMap.forEach((key, value) -> {
            Map<String, BigDecimal> devMap = timeDetailMap1.getOrDefault(key, new HashMap<>());
            if (MapUtils.isEmpty(devMap)) {
                devMap.put("other", value);
                // 主电表多余dt放入结果map中的other中
                timeDetailMap1.put(key, devMap);
            }
        });
        dpStatListVO.setTimeDetailMap(timeDetailMap1);
        return dpStatListVO;
    }

    private static Map<String, JSONObject> getDataPointBOMap() {
        String _a =
                "{\"bf0d45d5f63deb6e30aj1v\":{\"notConfig\":false,\"109\":\"Heating\",\"label\":\"Heating\"},\"bf2bbe11d2967a262ccqok\":{\"notConfig\":false,\"109\":\"Total\",\"label\":\"Total\"},\"bf32e85ec876bdaeb3xprc\":{\"notConfig\":false,\"109\":\"Hot_Water\",\"label\":\"Hot_Water\"},\"bfb9dfd66e1178fcfa6xw1\":{\"notConfig\":false,\"109\":\"Cooktop\",\"label\":\"Cooktop\"}}";
        return JSONObject.parseObject(_a, new TypeReference<>() {
        });
    }

    private static List<SolutionDeviceDpStatBaseEntityVO> getSolutionDeviceDpStatBaseEntityList() {
        String _a =
                "[{\"devId\":\"bf0d45d5f63deb6e30aj1v\",\"dpId\":\"114\",\"dt\":\"2022050603\",\"price\":2978.00,\"rangeValue\":2978,\"timeType\":0},{\"devId\":\"bf0d45d5f63deb6e30aj1v\",\"dpId\":\"114\",\"dt\":\"2022050601\",\"price\":8101.00,\"rangeValue\":8101,\"timeType\":0},{\"devId\":\"bf0d45d5f63deb6e30aj1v\",\"dpId\":\"114\",\"dt\":\"2022050602\",\"price\":11543.00,\"rangeValue\":11543,\"timeType\":0},{\"devId\":\"bf32e85ec876bdaeb3xprc\",\"dpId\":\"114\",\"dt\":\"2022050610\",\"price\":325.00,\"rangeValue\":325,\"timeType\":0},{\"devId\":\"bf32e85ec876bdaeb3xprc\",\"dpId\":\"114\",\"dt\":\"2022050611\",\"price\":563.00,\"rangeValue\":563,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050604\",\"price\":54.00,\"rangeValue\":54,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050600\",\"price\":60.00,\"rangeValue\":60,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050607\",\"price\":63.00,\"rangeValue\":63,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050605\",\"price\":65.00,\"rangeValue\":65,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050606\",\"price\":78.00,\"rangeValue\":78,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050620\",\"price\":84.00,\"rangeValue\":84,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050623\",\"price\":87.00,\"rangeValue\":87,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050613\",\"price\":87.00,\"rangeValue\":87,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050615\",\"price\":93.00,\"rangeValue\":93,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050612\",\"price\":101.00,\"rangeValue\":101,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050616\",\"price\":104.00,\"rangeValue\":104,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050621\",\"price\":106.00,\"rangeValue\":106,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050622\",\"price\":109.00,\"rangeValue\":109,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050614\",\"price\":109.00,\"rangeValue\":109,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050608\",\"price\":11800.00,\"rangeValue\":118,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050609\",\"price\":159.00,\"rangeValue\":159,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050619\",\"price\":202.00,\"rangeValue\":202,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050618\",\"price\":360.00,\"rangeValue\":360,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050617\",\"price\":396.00,\"rangeValue\":396,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050610\",\"price\":481.00,\"rangeValue\":481,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050611\",\"price\":656.00,\"rangeValue\":656,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050602\",\"price\":6221.00,\"rangeValue\":6221,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050601\",\"price\":7985.00,\"rangeValue\":7985,\"timeType\":0},{\"devId\":\"bfb9dfd66e1178fcfa6xw1\",\"dpId\":\"114\",\"dt\":\"2022050619\",\"price\":57.00,\"rangeValue\":57,\"timeType\":0},{\"devId\":\"bfb9dfd66e1178fcfa6xw1\",\"dpId\":\"114\",\"dt\":\"2022050618\",\"price\":264.00,\"rangeValue\":264,\"timeType\":0},{\"devId\":\"bfb9dfd66e1178fcfa6xw1\",\"dpId\":\"114\",\"dt\":\"2022050617\",\"price\":275.00,\"rangeValue\":275,\"timeType\":0}]";
        return JSONObject.parseObject(_a, new TypeReference<>() {
        });
    }

    private static SolutionTariffPlanEntity getSolutionTariffPlanEntity() {
        String _a =
                "{\"consumptionMonthTarget\":1000.00,\"currency\":\"eur\",\"gmtCreate\":1634642475357,\"gmtModified\":1650620622,\"id\":38,\"onPeakEndTimeFormate\":\"08\",\"onPeakStartTimeFormate\":\"08\",\"planType\":2,\"platRate\":1.00,\"platRateOnPeak\":100.00,\"productMonthTarget\":1000.00,\"resId\":\"18391695\",\"resType\":1,\"status\":1}";
        return JSONObject.parseObject(_a, SolutionTariffPlanEntity.class);
    }

    public static BigDecimal mapStatTypeBigDecimal(StatTypeEnum statType, SolutionDeviceDpStatBaseEntityVO entity) {
        if (StatTypeEnum.USAGE == statType && entity.getRangeValue() != null) {
            return entity.getRangeValue();
        }
        return BigDecimal.ZERO;
    }
}

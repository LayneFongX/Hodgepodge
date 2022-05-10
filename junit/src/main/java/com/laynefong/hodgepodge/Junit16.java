package com.laynefong.hodgepodge;

import com.laynefong.hodgepodge.domain.DpHistoryElementAdaptVO;
import com.laynefong.hodgepodge.domain.SolutionDeviceDpStatBaseEntity;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author Banchao
 * @Date 2022/3/31 18:09
 */
public class Junit16 {

    public static final DateTimeFormatter YYYY_MM_DD_HH = DateTimeFormat.forPattern("yyyyMMddHH");

    public static void main(String[] args) {
        List<Integer> dpIds = Arrays.asList(17);
        List<DpHistoryElementAdaptVO> dpHistoryElementList = new ArrayList<>();
        DpHistoryElementAdaptVO elementAdaptVO = new DpHistoryElementAdaptVO();
        elementAdaptVO.setDevId("bff8c5788597fab168ukha");
        elementAdaptVO.setDpId(17);
        elementAdaptVO.setDt("2022-03-31 17:59:01");
        elementAdaptVO.setTimeStamp(1648720741695L);
        elementAdaptVO.setValue("61");
        dpHistoryElementList.add(elementAdaptVO);

        DateTimeFormatter formatter = YYYY_MM_DD_HH.withZone(DateTimeZone.forID("Asia/Shanghai"));
        List<SolutionDeviceDpStatBaseEntity> solutionDeviceDpStatBaseEntities =
                fullHistoryToEntityList(dpIds, formatter, dpHistoryElementList);
        System.out.println(solutionDeviceDpStatBaseEntities);

    }

    private static List<SolutionDeviceDpStatBaseEntity> fullHistoryToEntityList(List<Integer> dpIds,
                                                                                DateTimeFormatter formatter,
                                                                                List<DpHistoryElementAdaptVO> dpHistoryElementList) {


        //devId，dpId，时间格式，对应最大值
        final Map<String, Map<Integer, Map<String, Optional<DpHistoryElementAdaptVO>>>> timeMaxMap =
                dpHistoryElementList.stream()
                        .collect(Collectors.groupingBy(m -> m.getDevId(),
                                Collectors.groupingBy(m -> m.getDpId(),
                                        Collectors.groupingBy(m -> new DateTime(m.getTimeStamp()).toString(formatter),
                                                Collectors.maxBy((o1, o2) -> (int) (NumberUtils.toLong(o1.getValue()) -
                                                        NumberUtils.toLong(o2.getValue())))))));


        //devId，dpId，时间格式，对应最小值
        final Map<String, Map<Integer, Map<String, Optional<DpHistoryElementAdaptVO>>>> timeMinMap =
                dpHistoryElementList.stream()
                        .collect(Collectors.groupingBy(m -> m.getDevId(),
                                Collectors.groupingBy(m -> m.getDpId(),
                                        Collectors.groupingBy(m -> new DateTime(m.getTimeStamp()).toString(formatter),
                                                Collectors.minBy((o1, o2) -> (int) (NumberUtils.toLong(o1.getValue()) -
                                                        NumberUtils.toLong(o2.getValue())))))));

        //times、devIds
        List<String> times1 =
                timeMaxMap.values().stream().flatMap(it -> it.values().stream().flatMap(iit -> iit.keySet().stream())).distinct()
                        .collect(Collectors.toList());

        List<String> devIdList = dpHistoryElementList.stream()
                .map(DpHistoryElementAdaptVO::getDevId)
                .distinct().collect(Collectors.toList());


        //组装数据
        return devIdList.stream()
                .filter(devId -> timeMaxMap.get(devId) != null && timeMinMap.get(devId) != null)
                .flatMap(
                        devId -> dpIds.stream()
                                .filter(dpId -> timeMaxMap.get(devId).get(dpId) != null && timeMinMap.get(devId).get(dpId) != null)
                                .flatMap(dpId -> times1.stream()
                                        .filter(dt -> timeMaxMap.get(devId).get(dpId).get(dt) != null &&
                                                timeMinMap.get(devId).get(dpId).get(dt) != null)
                                        .map(dt -> {
                                            //这个设备，最大值、最小值
                                            Optional<DpHistoryElementAdaptVO> optionalMax =
                                                    timeMaxMap.get(devId).get(dpId).get(dt);
                                            Optional<DpHistoryElementAdaptVO> optionalMin =
                                                    timeMinMap.get(devId).get(dpId).get(dt);
                                            if (optionalMax.isPresent() && optionalMin.isPresent()) {
                                                SolutionDeviceDpStatBaseEntity entity =
                                                        new SolutionDeviceDpStatBaseEntity();
                                                entity.setDevId(devId);
                                                entity.setDt(dt);
                                                entity.setDpId(String.valueOf(dpId));
                                                long rangValue = NumberUtils.toLong(optionalMax.get().getValue()) -
                                                        NumberUtils.toLong(optionalMin.get().getValue());
                                                entity.setRangeValue(new BigDecimal(rangValue));
                                                return entity;
                                            }
                                            return null;
                                        }).filter(Objects::nonNull)).filter(Objects::nonNull)
                ).collect(Collectors.toList());
    }


}

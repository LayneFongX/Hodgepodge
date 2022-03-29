package com.laynefong.hodgepodge;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.laynefong.hodgepodge.domain.DpHistoryElementAdaptVO;
import com.laynefong.hodgepodge.domain.SolutionDeviceDpStatBaseEntity;
import com.laynefong.hodgepodge.domain.basedo.DeviceVO;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.data.util.Pair;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @Author Banchao
 * @Date 2022/3/29 12:14
 */
public class Junit15 {

    public static final DateTimeFormatter YYYY_MM_DD_HH = DateTimeFormat.forPattern("yyyyMMddHH");

    public static final String YYYY_MM_DD_PATTERN = "yyyyMMdd";

    public static final String YYYY_MM_DD_HH_PATTERN = "yyyyMMddHH";

    public static final long STEP_HOUR = 1000 * 60 * 60 * 12;

    public static final long MILL_OF_HOUR = 3600000;

    public static void main(String[] args) {
        List<String> dts = Arrays.asList("2022032800", "2022032801", "2022032802", "2022032803", "2022032804", "2022032805", "2022032806",
                "2022032807", "2022032808", "2022032809", "2022032810", "2022032811", "2022032812");
        String timeZoneId = "Europe/Prague";
        List<String> devIds = Collections.singletonList("bf0d45d5f63deb6e30aj1v");
        List<String> dpIds = Arrays.asList("114", "115");

        String startHour = dts.get(0);
        String endHour = dts.get(dts.size() - 1);
        DateTimeFormatter formatter = YYYY_MM_DD_HH.withZone(DateTimeZone.forID(timeZoneId));
        DateTime startTime = DateTime.parse(startHour, formatter);
        DateTime endTime = DateTime.parse(endHour, formatter);

        List<Integer> dps = dpIds.stream().map(Integer::valueOf).distinct().collect(Collectors.toList());
        List<SolutionDeviceDpStatBaseEntity> solutionDeviceDpStatBaseEntities =
                queryDpHistory(devIds, dps, startTime.getMillis(), endTime.getMillis() + MILL_OF_HOUR - 1, timeZoneId);
        System.out.println(solutionDeviceDpStatBaseEntities);
    }

    @SneakyThrows
    public static List<SolutionDeviceDpStatBaseEntity> queryDpHistory(final List<String> devIds, final List<Integer> dpIds, Long startTime,
                                                                      Long endTime, String timeZoneId) {

        long start = System.currentTimeMillis();
        DateTimeFormatter formatter = YYYY_MM_DD_HH.withZone(DateTimeZone.forID(timeZoneId));

        // 从缓存获取数据
        List<String> dpIdStrList = dpIds.stream().map(Object::toString).collect(Collectors.toList());
        String cacheValue =
                "[{\"devId\":\"bf0d45d5f63deb6e30aj1v\",\"dpId\":\"114\",\"dt\":\"2022032708\",\"rangeValue\":8753,\"timeType\":0}," +
                        "{\"devId\":\"bf0d45d5f63deb6e30aj1v\",\"dpId\":\"114\",\"dt\":\"2022032709\",\"rangeValue\":9514,\"timeType\":0}," +
                        "{\"devId\":\"bf0d45d5f63deb6e30aj1v\",\"dpId\":\"114\",\"dt\":\"2022032609\",\"rangeValue\":1940,\"timeType\":0}," +
                        "{\"devId\":\"bf0d45d5f63deb6e30aj1v\",\"dpId\":\"114\",\"dt\":\"2022032607\",\"rangeValue\":8851,\"timeType\":0}," +
                        "{\"devId\":\"bf0d45d5f63deb6e30aj1v\",\"dpId\":\"114\",\"dt\":\"2022032618\",\"rangeValue\":9463,\"timeType\":0}," +
                        "{\"devId\":\"bf0d45d5f63deb6e30aj1v\",\"dpId\":\"114\",\"dt\":\"2022032608\",\"rangeValue\":9932,\"timeType\":0}," +
                        "{\"devId\":\"bf0d45d5f63deb6e30aj1v\",\"dpId\":\"114\",\"dt\":\"2022032619\",\"rangeValue\":1824,\"timeType\":0}," +
                        "{\"devId\":\"bf0d45d5f63deb6e30aj1v\",\"dpId\":\"114\",\"dt\":\"2022032617\",\"rangeValue\":10007,\"timeType\":0}," +
                        "{\"devId\":\"bf0d45d5f63deb6e30aj1v\",\"dpId\":\"114\",\"dt\":\"2022032710\",\"rangeValue\":1746,\"timeType\":0}]";
        List<SolutionDeviceDpStatBaseEntity> cacheList =
                JSONObject.parseObject(cacheValue, new TypeReference<List<SolutionDeviceDpStatBaseEntity>>() {
                });


        // 通过和缓存中数据与查询时间对比，求出缓存未覆盖的时间区间，进行查询
        List<Pair<Long, Long>> timePairList = getTimePairAndRemoveExpire(startTime, endTime, timeZoneId, cacheList);

        // 截取查询范围内数据
        List<SolutionDeviceDpStatBaseEntity> rangeOfList = cacheList.stream().filter(dpHistory -> {
            long dtTimestamp = dtToTimestamp(dpHistory.getDt(), timeZoneId);
            return dtTimestamp >= startTime && dtTimestamp <= endTime;
        }).collect(Collectors.toList());


        if (timePairList == null) {
            // 等于null说明缓存已包含所有数据，直接返回缓存数据
            return rangeOfList;
        }

        List<DpHistoryElementAdaptVO> dataPointStatisticList = new LinkedList<>();
        for (Pair<Long, Long> timePair : timePairList) {
            // 按单个设备、时间步长切分查询条件，多线程查询
            dataPointStatisticList.addAll(getDpHistoryListMultithreading(devIds, dpIds, timePair.getFirst(), timePair.getSecond()));
        }

        if (CollectionUtils.isEmpty(dataPointStatisticList)) {
            return rangeOfList;
        }

        Set<String> devIdSet = new HashSet<>();
        //过滤无效数据
        List<DpHistoryElementAdaptVO> dpHistoryElementList = dataPointStatisticList.stream()
                .filter(Objects::nonNull)
                .filter(it -> dpIds.contains(it.getDpId()))
                .filter(it -> NumberUtils.isNumber(it.getValue()))
                .peek(history -> devIdSet.add(history.getDevId()))
                .collect(Collectors.toList());

        if (dpHistoryElementList.size() != dataPointStatisticList.size()) {
            System.out.println("存在脏数据");
        }
        if (CollectionUtils.isEmpty(dpHistoryElementList)) {
            System.out.println("过滤后没有");
            return Collections.emptyList();
        }

        /**
         *  DpHistoryElementAdaptVO 转换为 SolutionDeviceDpStatBaseEntity
         */
        List<SolutionDeviceDpStatBaseEntity> result = fillEntity(dpIds, start, formatter, new ArrayList<>(devIdSet), dpHistoryElementList);

        // 缓存中截取的数据+实时查询数据为返回结果
        rangeOfList.addAll(result);

        // 缓存中的所有数据+实时查询数据为最新缓存中的数据
        cacheList.addAll(result);
        // if (CollectionUtils.isNotEmpty(cacheList)) {
        //     // 最新数据放入缓存
        //     dpStatLokiClient.opsForValue().set(cacheKey, cacheList, CACHE_TIME_OUT, TimeUnit.MILLISECONDS);
        // }
        return rangeOfList;
    }

    /**
     * 根据缓存数据和查询时间区间进行对比，计算出缓存外需要查询的时间区间
     *
     * @param startTime  开始时间
     * @param endTime    结束时间
     * @param timeZoneId 时区ID
     * @param cacheList  缓存数据
     * @return List<Pair < Long, Long>> 为null则代表所有数据都可从缓存中获取
     */
    private static List<Pair<Long, Long>> getTimePairAndRemoveExpire(Long startTime, Long endTime, String timeZoneId,
                                                                     List<SolutionDeviceDpStatBaseEntity> cacheList) {
        List<Pair<Long, Long>> pairs = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(cacheList)) {
            // 移除掉昨天之前的数据
            String twoDaysAgoDt = "20220327";
            cacheList.removeIf(next -> {
                // 历史数据DT小于昨天前的DT，移除。小时位置补零*100
                return (Integer.parseInt(twoDaysAgoDt) * 100) > Integer.parseInt(next.getDt());
            });

            // 移除过期数据之后无数据查询所有区间数据
            if (CollectionUtils.isEmpty(cacheList)) {
                pairs.add(Pair.of(startTime, endTime));
                return pairs;
            }

            // 求缓存中最大和最小DT
            SolutionDeviceDpStatBaseEntity maxTimeDpHistory =
                    Collections.max(cacheList, Comparator.comparingInt(dp -> Integer.parseInt(dp.getDt())));
            SolutionDeviceDpStatBaseEntity minTimeDpHistory =
                    Collections.min(cacheList, Comparator.comparingInt(dp -> Integer.parseInt(dp.getDt())));
            String minDt = minTimeDpHistory.getDt();
            String maxDt = maxTimeDpHistory.getDt();
            int maxDtInt = Integer.parseInt(maxDt);
            int minDtInt = Integer.parseInt(minDt);

            // 判断缓存是否完全包含startTime之后的数据
            String startTimeDt = timestampToDt(startTime, timeZoneId);
            // 判断缓存是完全否包含当前endTime之前的数据
            String endTimeDt = timestampToDt(endTime, timeZoneId);

            // 缓存中最大缓存数据小于真实查询开始时间
            if (maxDtInt <= Integer.parseInt(startTimeDt) || minDtInt >= Integer.parseInt(endTimeDt)) {
                pairs.add(Pair.of(startTime, endTime));
                return pairs;
            }

            // 缓存中最小缓存数据大于真实查询结束时间
            if (minDtInt >= Integer.parseInt(endTimeDt)) {
                pairs.add(Pair.of(startTime, endTime));
                return pairs;
            }

            if (maxDtInt <= Integer.parseInt(endTimeDt)) {
                // 开始时间为最大dt，结束时间为传入的endTime
                Pair<Long, Long> timePair = Pair.of(dtToTimestamp(maxDt, timeZoneId), endTime);
                pairs.add(timePair);
                // 最后一个小时数据移除，查询最新数据
                cacheList.removeIf(next -> next.getDt().equals(maxDt));
            }


            if (minDtInt > Integer.parseInt(startTimeDt)) {
                // 开始时间为最大dt，结束时间为传入的endTime
                Pair<Long, Long> timePair = Pair.of(startTime, dtToTimestamp(minDt, timeZoneId));
                pairs.add(timePair);
            }

            // 最小的dt小于starTime，说明前面的不用查了，最大dt大于endTime，说明后面不用查了
            if (minDtInt <= Integer.parseInt(startTimeDt) && maxDtInt > Integer.parseInt(endTimeDt)) {
                return null;
            }
        } else {
            // 缓存无数据按传入的开始时间和结束时间进行查询
            pairs.add(Pair.of(startTime, endTime));
        }

        return pairs;
    }


    public static String timestampToDt(Long timestamp, String timeZoneId) {
        LocalDateTime localDateTime = new LocalDateTime(timestamp, DateTimeZone.forID(timeZoneId));
        return localDateTime.toString(YYYY_MM_DD_HH_PATTERN);
    }

    public static long dtToTimestamp(String dt, String timeZonId) {
        DateTimeZone zone = DateTimeZone.forID(timeZonId);
        Chronology chronology = new LocalDateTime(zone).getChronology().withZone(zone);

        return chronology.getDateTimeMillis(Integer.parseInt(dt.substring(0, 4)),
                Integer.parseInt(dt.substring(4, 6)),
                Integer.parseInt(dt.substring(6, 8)),
                Integer.parseInt(dt.substring(8, 10)), 0, 0, 0);
    }

    private static List<SolutionDeviceDpStatBaseEntity> fillEntity(List<Integer> dpIds, long start, DateTimeFormatter formatter,
                                                                   List<String> devIdList,
                                                                   List<DpHistoryElementAdaptVO> dpHistoryElementList) {

        String value =
                "{\"bf0d45d5f63deb6e30aj1v\":{\"ability\":0,\"accessType\":3,\"activeTime\":1646647056,\"customName\":\"Heating\",\"devAttribute\":0,\"devEtag\":\"\",\"devKey\":\"\",\"etag\":\"\",\"icon\":\"smart/device_icon/eu1604914870612ykKW5/bf0d45d5f63deb6e30aj1v164664892251396.png\",\"id\":\"bf0d45d5f63deb6e30aj1v\",\"ip\":\"\",\"lang\":\"en\",\"lat\":\"49.8926\",\"localKey\":\"\",\"lon\":\"18.1062\",\"mac\":\"00000000e202aad7\",\"name\":\"PowerTag 4\",\"ownerId\":\"18391695\",\"port\":-1,\"productId\":\"84db4ryf\",\"productKey\":\"\",\"restoreFactory\":false,\"runtimeEnv\":\"prod\",\"secKey\":\"\",\"skill\":\"\",\"status\":true,\"sub\":true,\"timeZone\":\"+01:00\",\"timeZoneId\":\"Europe/Prague\",\"uid\":\"eu1604489923468T2l4z\",\"uuid\":\"bf0d45d5f63deb6e30aj1v\",\"verProtocol\":\"\"}}";
        // 查询设备信息
        Map<String, DeviceVO> devMap = JSONObject.parseObject(value, new TypeReference<>() {
        });
        // 判断当前设备列表是否有增量上报设备
        Boolean hasIncrDev = hashIncrDev(devIdList, devMap);

        // 处理增量上报设备数据
        List<SolutionDeviceDpStatBaseEntity> incrEntityList = Collections.emptyList();
        if (hasIncrDev) {
            // 按设备增量和全量上报的维度进行数据分组
            Map<Boolean, List<DpHistoryElementAdaptVO>> devListMap =
                    dpHistoryElementList.stream().collect(Collectors.groupingBy(history -> isIncrDpDev(devMap, history.getDevId())));
            // 增量上报设备列表
            List<DpHistoryElementAdaptVO> incrDevHistories = devListMap.get(Boolean.TRUE);
            // 过滤掉增量的设备之后重新赋值
            dpHistoryElementList = devListMap.get(Boolean.FALSE);

            // 转换DTO
            incrEntityList = incrHistoryToEntityList(formatter, incrDevHistories);
        }


        // 没有全量上报的设备数据直接返回
        if (dpHistoryElementList == null || dpHistoryElementList.isEmpty()) {
            return incrEntityList;
        }

        // 处理全量上报数据
        List<SolutionDeviceDpStatBaseEntity> result = fullHistoryToEntityList(dpIds, start, formatter, devIdList, dpHistoryElementList);


        // 返回结果，哪个结果集大做为基础集合，减少数组扩容几率
        if (incrEntityList.size() > result.size()) {
            incrEntityList.addAll(result);
            return incrEntityList;
        }

        result.addAll(incrEntityList);
        return result;
    }

    /**
     * 当前设备列表是否有增量上报设备
     *
     * @param devIdList 设备列表
     * @param devMap    缓存中获取的设备信息
     * @return Boolean
     */
    public static Boolean hashIncrDev(List<String> devIdList, Map<String, DeviceVO> devMap) {
        for (String devId : devIdList) {
            if (isIncrDpDev(devMap, devId)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * 判断是否增量上报的设备
     *
     * @param devMap 设备缓存map
     * @param devId  设备ID
     * @return true/false
     */
    public static boolean isIncrDpDev(Map<String, DeviceVO> devMap, String devId) {
        DeviceVO deviceVo = devMap.get(devId);
        if (deviceVo != null) {
            // 判断是否增量上报设备
            return isIncrDpDev(deviceVo.getProductId());
        }
        return false;
    }

    /**
     * 从配置中心获取增量上报日志设备Pid列表
     *
     * @return Set
     */
    public static Set<String> getIncrDpPidList() {
        String[] split = new String[1];
        split[0] = "6c25d3c901d3ed0bbf3pin";
        return Arrays.stream(split).collect(Collectors.toSet());
    }

    public static Boolean isIncrDpDev(String devId) {
        return getIncrDpPidList().contains(devId);
    }

    public static List<SolutionDeviceDpStatBaseEntity> incrHistoryToEntityList(DateTimeFormatter formatter,
                                                                               List<DpHistoryElementAdaptVO> incrHistorys) {
        List<SolutionDeviceDpStatBaseEntity> incrDpDevList = new ArrayList<>();

        // 按设备、dp点、dt维度进行分组合并，value值相加
        Map<String, Map<Integer, Map<String, DpHistoryElementAdaptVO>>> stringMapMap = incrHistorys.stream()
                .peek(history -> history.setDt(new DateTime(history.getTimeStamp()).toString(formatter)))
                .collect(Collectors.groupingBy(DpHistoryElementAdaptVO::getDevId,
                        Collectors.groupingBy(DpHistoryElementAdaptVO::getDpId,
                                Collectors.toMap(DpHistoryElementAdaptVO::getDt, history -> history, (h1, h2) -> {
                                    Long h1Val = h1.getValue() == null ? 0L : Integer.parseInt(h1.getValue());
                                    Long h2Val = h2.getValue() == null ? 0L : Integer.parseInt(h2.getValue());
                                    long rangeValue = h1Val + h2Val;
                                    h1.setValue(String.valueOf(rangeValue));
                                    return h1;
                                }))));

        // 遍历转换为entity列表
        stringMapMap.values().forEach(map -> {
            for (Map<String, DpHistoryElementAdaptVO> valueMap : map.values()) {
                for (DpHistoryElementAdaptVO historyVo : valueMap.values()) {

                    SolutionDeviceDpStatBaseEntity entity = new SolutionDeviceDpStatBaseEntity();
                    entity.setDevId(historyVo.getDevId());
                    entity.setDt(historyVo.getDt());
                    entity.setDpId(String.valueOf(historyVo.getDpId()));
                    String value = historyVo.getValue();
                    long rangeValue = StringUtils.isNumeric(value) ? Long.parseLong(value) : 0L;
                    entity.setRangeValue(new BigDecimal(rangeValue));
                    incrDpDevList.add(entity);
                }
            }
        });
        return incrDpDevList;
    }

    private static List<SolutionDeviceDpStatBaseEntity> fullHistoryToEntityList(List<Integer> dpIds, long start,
                                                                                DateTimeFormatter formatter,
                                                                                List<String> devIdList,
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
                                            Optional<DpHistoryElementAdaptVO> optionalMax = timeMaxMap.get(devId).get(dpId).get(dt);
                                            Optional<DpHistoryElementAdaptVO> optionalMin = timeMinMap.get(devId).get(dpId).get(dt);
                                            if (optionalMax.isPresent() && optionalMin.isPresent()) {
                                                SolutionDeviceDpStatBaseEntity entity = new SolutionDeviceDpStatBaseEntity();
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

    @SneakyThrows
    private static List<DpHistoryElementAdaptVO> getDpHistoryListMultithreading(List<String> devIds, List<Integer> dpIds, Long startTime,
                                                                                Long endTime) {
        // 查询最新的实时数据
        List<CompletableFuture<List<DpHistoryElementAdaptVO>>> futureList = new ArrayList<>(devIds.size() * 2);
        List<Pair<Long, Long>> timeStepList = getTimeStep(startTime, endTime, STEP_HOUR);
        for (String devId : devIds) {
            for (Pair<Long, Long> timeStep : timeStepList) {
                System.out.println("devIdList = " + JSONObject.toJSONString(Collections.singletonList(devId)) + ",dpIds = " +
                        JSONObject.toJSONString(dpIds) + ",startTime = " + timeStep.getFirst() + ",endTime = " + timeStep.getSecond());
                // CompletableFuture<List<DpHistoryElementAdaptVO>> completableFuture = CompletableFuture.supplyAsync(() ->
                //                 dpStatisticsAdaptService.getDpHistoryNew(Collections.singletonList(devId), dpIds, timeStep.getFirst(),
                //                         timeStep.getSecond(), batchSize, delayMillis)
                //         , Constant.DP_QUERY_FUTURE_THREAD_POOL);
                // futureList.add(completableFuture);
            }
        }
        // 阻塞所有线程
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0])).join();

        List<DpHistoryElementAdaptVO> historyList = new LinkedList<>();
        for (CompletableFuture<List<DpHistoryElementAdaptVO>> future : futureList) {
            historyList.addAll(future.get());
        }
        return historyList;
    }

    /**
     * 毫秒数按照步长分区
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param step      步长
     * @return Pair<startTime, endTime>
     */
    public static List<Pair<Long, Long>> getTimeStep(Long startTime, Long endTime, Long step) {
        boolean first = true;
        List<Pair<Long, Long>> timeStepList = new ArrayList<>();
        while ((endTime - startTime) > 0) {
            long nextEndTime;

            if (first) {
                nextEndTime = endTime;
                first = false;
            } else {
                nextEndTime = endTime - 1;
            }

            if ((endTime -= step) < startTime) {
                endTime = startTime;
            }
            // first->开始时间 second—>结束时间
            timeStepList.add(Pair.of(endTime, nextEndTime));
        }
        return timeStepList;
    }

}

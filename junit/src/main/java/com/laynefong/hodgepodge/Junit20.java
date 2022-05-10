package com.laynefong.hodgepodge;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.laynefong.hodgepodge.domain.DpHistoryElementAdaptVO;
import com.laynefong.hodgepodge.domain.SolutionDeviceDpStatBaseEntity;
import com.laynefong.hodgepodge.uitls.TimeUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.data.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author Banchao
 * @Date 2022/5/9 19:31
 */
public class Junit20 {

    public static void main(String[] args) {
        List<String> devIds =
                Arrays.asList("bf1b41d36e30836d4fkeoz", "bf0d45d5f63deb6e30aj1v", "bf32e85ec876bdaeb3xprc", "bfb9dfd66e1178fcfa6xw1",
                        "bf2bbe11d2967a262ccqok");
        Long startTime = 1651960800000L;
        Long endTime = 1652093999999L;
        String timeZoneId = "Europe/Prague";

        queryDpHistory(devIds, Arrays.asList(114, 115), startTime, endTime, timeZoneId);
    }

    public static List<SolutionDeviceDpStatBaseEntity> queryDpHistory(final List<String> devIds, final List<Integer> dpIds,
                                                                      Long startTime,
                                                                      Long endTime, String timeZoneId) {

        long start = System.currentTimeMillis();
        DateTimeFormatter formatter = TimeUtils.YYYY_MM_DD_HH.withZone(DateTimeZone.forID(timeZoneId));

        // 从缓存获取数据
        List<String> dpIdStrList = dpIds.stream().map(Object::toString).collect(Collectors.toList());
        List<SolutionDeviceDpStatBaseEntity> dpCacheList = getDpCacheList();
        List<SolutionDeviceDpStatBaseEntity> cacheList =
                dpCacheList.stream().filter(x -> x.getDevId().equals("bf2bbe11d2967a262ccqok")).collect(
                        Collectors.toList());
        Collections.sort(cacheList, new Comparator<SolutionDeviceDpStatBaseEntity>() {
            @Override
            public int compare(SolutionDeviceDpStatBaseEntity o1, SolutionDeviceDpStatBaseEntity o2) {
                return o1.getDt().compareTo(o2.getDt());
            }
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
            // dataPointStatisticList.addAll(getDpHistoryListMultithreading(devIds, dpIds, timePair.getFirst(),
            //         timePair.getSecond()));
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

        if (CollectionUtils.isEmpty(dpHistoryElementList)) {
            return Collections.emptyList();
        }

        /**
         *  DpHistoryElementAdaptVO 转换为 SolutionDeviceDpStatBaseEntity
         */
        // List<SolutionDeviceDpStatBaseEntity> result = fillEntity(dpIds, start, formatter, new ArrayList<>(devIdSet),
        //         dpHistoryElementList);

        // List<SolutionDeviceDpStatBaseEntity> castClassResult = new ArrayList<>(result);
        // // 缓存中截取的数据+实时查询数据为返回结果
        // rangeOfList.addAll(castClassResult);
        // // 缓存中的所有数据+实时查询数据为最新缓存中的数据
        // cacheList.addAll(castClassResult);
        return rangeOfList;
    }


    public static long dtToTimestamp(String dt, String timeZonId) {
        DateTimeZone zone = DateTimeZone.forID(timeZonId);
        Chronology chronology = new LocalDateTime(zone).getChronology().withZone(zone);

        return chronology.getDateTimeMillis(Integer.parseInt(dt.substring(0, 4)),
                Integer.parseInt(dt.substring(4, 6)),
                Integer.parseInt(dt.substring(6, 8)),
                Integer.parseInt(dt.substring(8, 10)), 0, 0, 0);
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
            LocalDateTime localDateTime = LocalDateTime.now(DateTimeZone.forID(timeZoneId)).plusDays(-2);
            String threeDaysAgoDt = localDateTime.toString(TimeUtils.YYYY_MM_DD_PATTERN);
            cacheList.removeIf(next -> {
                // 历史数据DT小于昨天前的DT，移除。小时位置补零*100
                return (Integer.parseInt(threeDaysAgoDt) * 100) > Integer.parseInt(next.getDt());
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

            // 缓存中最大缓存数据小于真实查询开始时间 或 最小缓存数据大于真实查询结束时间
            if (maxDtInt <= Integer.parseInt(startTimeDt) || minDtInt >= Integer.parseInt(endTimeDt)) {
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
                //
                return null;
            }
        } else {
            // 缓存无数据按传入的开始时间和结束时间进行查询
            pairs.add(Pair.of(startTime, endTime));
        }
        // pairs.add(Pair.of(startTime, endTime));
        return pairs;
    }

    private static List<SolutionDeviceDpStatBaseEntity> getDpCacheList() {
        String _a =
                "[{\"devId\":\"bf32e85ec876bdaeb3xprc\",\"dpId\":\"114\",\"dt\":\"2022050900\",\"rangeValue\":115,\"timeType\":0},{\"devId\":\"bf32e85ec876bdaeb3xprc\",\"dpId\":\"114\",\"dt\":\"2022050901\",\"rangeValue\":475,\"timeType\":0},{\"devId\":\"bf32e85ec876bdaeb3xprc\",\"dpId\":\"114\",\"dt\":\"2022050906\",\"rangeValue\":602,\"timeType\":0},{\"devId\":\"bf32e85ec876bdaeb3xprc\",\"dpId\":\"114\",\"dt\":\"2022050810\",\"rangeValue\":797,\"timeType\":0},{\"devId\":\"bf32e85ec876bdaeb3xprc\",\"dpId\":\"114\",\"dt\":\"2022050812\",\"rangeValue\":811,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050712\",\"rangeValue\":8,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050910\",\"rangeValue\":43,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050814\",\"rangeValue\":65,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050905\",\"rangeValue\":69,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050807\",\"rangeValue\":81,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050709\",\"rangeValue\":82,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050900\",\"rangeValue\":82,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050813\",\"rangeValue\":83,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050804\",\"rangeValue\":85,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050816\",\"rangeValue\":85,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050818\",\"rangeValue\":85,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050823\",\"rangeValue\":86,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050700\",\"rangeValue\":88,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050806\",\"rangeValue\":88,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050711\",\"rangeValue\":89,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050819\",\"rangeValue\":89,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050707\",\"rangeValue\":93,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050708\",\"rangeValue\":95,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050706\",\"rangeValue\":96,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050904\",\"rangeValue\":96,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050817\",\"rangeValue\":96,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050704\",\"rangeValue\":100,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050710\",\"rangeValue\":105,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050800\",\"rangeValue\":108,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050815\",\"rangeValue\":108,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050808\",\"rangeValue\":109,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050705\",\"rangeValue\":111,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050909\",\"rangeValue\":114,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050911\",\"rangeValue\":116,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050805\",\"rangeValue\":117,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050822\",\"rangeValue\":124,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050820\",\"rangeValue\":168,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050908\",\"rangeValue\":175,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050907\",\"rangeValue\":241,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050811\",\"rangeValue\":251,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050809\",\"rangeValue\":273,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050821\",\"rangeValue\":347,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050906\",\"rangeValue\":821,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050810\",\"rangeValue\":876,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050812\",\"rangeValue\":960,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050803\",\"rangeValue\":3114,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050703\",\"rangeValue\":3136,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050903\",\"rangeValue\":3214,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050801\",\"rangeValue\":8170,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050701\",\"rangeValue\":8255,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050901\",\"rangeValue\":8588,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050902\",\"rangeValue\":9868,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050802\",\"rangeValue\":10192,\"timeType\":0},{\"devId\":\"bf2bbe11d2967a262ccqok\",\"dpId\":\"115\",\"dt\":\"2022050702\",\"rangeValue\":11736,\"timeType\":0},{\"devId\":\"bf0d45d5f63deb6e30aj1v\",\"dpId\":\"114\",\"dt\":\"2022050803\",\"rangeValue\":3029,\"timeType\":0},{\"devId\":\"bf0d45d5f63deb6e30aj1v\",\"dpId\":\"114\",\"dt\":\"2022050903\",\"rangeValue\":3097,\"timeType\":0},{\"devId\":\"bf0d45d5f63deb6e30aj1v\",\"dpId\":\"114\",\"dt\":\"2022050703\",\"rangeValue\":3123,\"timeType\":0},{\"devId\":\"bf0d45d5f63deb6e30aj1v\",\"dpId\":\"114\",\"dt\":\"2022050801\",\"rangeValue\":8048,\"timeType\":0},{\"devId\":\"bf0d45d5f63deb6e30aj1v\",\"dpId\":\"114\",\"dt\":\"2022050901\",\"rangeValue\":8106,\"timeType\":0},{\"devId\":\"bf0d45d5f63deb6e30aj1v\",\"dpId\":\"114\",\"dt\":\"2022050701\",\"rangeValue\":8187,\"timeType\":0},{\"devId\":\"bf0d45d5f63deb6e30aj1v\",\"dpId\":\"114\",\"dt\":\"2022050902\",\"rangeValue\":11563,\"timeType\":0},{\"devId\":\"bf0d45d5f63deb6e30aj1v\",\"dpId\":\"114\",\"dt\":\"2022050802\",\"rangeValue\":11566,\"timeType\":0},{\"devId\":\"bf0d45d5f63deb6e30aj1v\",\"dpId\":\"114\",\"dt\":\"2022050702\",\"rangeValue\":11737,\"timeType\":0},{\"devId\":\"bf1b41d36e30836d4fkeoz\",\"dpId\":\"114\",\"dt\":\"2022050808\",\"rangeValue\":0,\"timeType\":0},{\"devId\":\"bf1b41d36e30836d4fkeoz\",\"dpId\":\"114\",\"dt\":\"2022050810\",\"rangeValue\":350,\"timeType\":0},{\"devId\":\"bf1b41d36e30836d4fkeoz\",\"dpId\":\"114\",\"dt\":\"2022050809\",\"rangeValue\":431,\"timeType\":0},{\"devId\":\"bfb9dfd66e1178fcfa6xw1\",\"dpId\":\"114\",\"dt\":\"2022050907\",\"rangeValue\":51,\"timeType\":0},{\"devId\":\"bfb9dfd66e1178fcfa6xw1\",\"dpId\":\"114\",\"dt\":\"2022050821\",\"rangeValue\":117,\"timeType\":0}]";
        return JSONObject.parseObject(_a, new TypeReference<>() {
        });
    }

    public static String timestampToDt(Long timestamp, String timeZoneId) {
        LocalDateTime localDateTime = new LocalDateTime(timestamp, DateTimeZone.forID(timeZoneId));
        return localDateTime.toString(TimeUtils.YYYY_MM_DD_HH_PATTERN);
    }

}

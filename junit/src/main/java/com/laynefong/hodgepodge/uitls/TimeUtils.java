package com.laynefong.hodgepodge.uitls;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * @author created by Zeb灬D on 2020-05-23 15:40
 */
@Slf4j
public class TimeUtils {
    public static final DateTimeFormatter YYYY = DateTimeFormat.forPattern("yyyy");
    public static final DateTimeFormatter YYYY_MM = DateTimeFormat.forPattern("yyyyMM");
    public static final DateTimeFormatter YYYY_MM_DD = DateTimeFormat.forPattern("yyyyMMdd");
    public static final DateTimeFormatter YYYY_MM_DD_HH = DateTimeFormat.forPattern("yyyyMMddHH");

    public static final String YYYY_PATTERN = "yyyy";
    public static final String YYYY_MM_PATTERN = "yyyyMM";
    public static final String YYYY_MM_DD_PATTERN = "yyyyMMdd";
    public static final String YYYY_MM_DD_HH_PATTERN = "yyyyMMddHH";

    /**
     * 中国境内时区ID列表
     */
    private static final Set<String> CHINA_TIME_ZONE_ID_SET =
            new HashSet<>(Arrays.asList("Asia/Chongqing", "Asia/Harbin", "Asia/Hong_Kong", "Asia/Macau", "Asia/Shanghai", "Asia/Taipei"));


    public static String toWeek(String day) {
        DateTime today = DateTime.parse(day, YYYY_MM_DD);
        DateTime mondayLastWeek = today.withDayOfWeek(DateTimeConstants.MONDAY);
        return mondayLastWeek.toString(YYYY_MM_DD);
    }


    public static String getMonthFirstDay() {
        DateTime today = DateTime.now();
        //DateTime today = DateTime.parse("20210428", YYYY_MM_DD);
        DateTime.Property mondayFirstDay = today.dayOfMonth();
        return mondayFirstDay.withMinimumValue().toString(YYYY_MM_DD);
    }

    //昨天的格式
    public static String getThisCurDay() {
        return DateTime.now().toString(YYYY_MM_DD);
    }

    //昨天的格式
    public static String getBeforeCurDay() {
        DateTime today = DateTime.now().minusDays(1);
        return today.toString(YYYY_MM_DD);
    }

    public static List<String> getHoursOfCurDay(String timeZoneId) {
        return getHoursOfCurDay(DateTime.now(DateTimeZone.forID(timeZoneId)));
    }

    public static List<String> getHoursOfYesterday(String timeZoneId) {
        DateTime now = DateTime.now(DateTimeZone.forID(timeZoneId));
        // 昨天的小时设置为23，防止今日的小时数影响到昨天的24个小时
        DateTime yesterday = now.plusDays(-1).withHourOfDay(23);
        return getHoursOfCurDay(yesterday);
    }

    public static List<String> getHoursOfBefore2Days(String timeZoneId) {
        DateTime now = DateTime.now(DateTimeZone.forID(timeZoneId));
        // 昨天的小时设置为23，防止今日的小时数影响到昨天的24个小时
        DateTime before2Days = now.plusDays(-2).withHourOfDay(23);
        return getHoursOfCurDay(before2Days);
    }

    public static List<String> getHoursOfCurDay() {
        return getHoursOfCurDay(DateTime.now());
    }

    public static List<String> getHoursOfCurDay(DateTime dateTime) {
        DateTime nowHour = DateTime.parse(dateTime.toString(YYYY_MM_DD_HH), YYYY_MM_DD_HH);
        DateTime dayFirstHour = nowHour.hourOfDay().withMinimumValue();
        List<String> hours = new LinkedList<>();
        while (nowHour.isAfter(dayFirstHour) || nowHour.equals(dayFirstHour)) {
            hours.add(dayFirstHour.toString(YYYY_MM_DD_HH));
            dayFirstHour = dayFirstHour.plusHours(1);
        }
        return hours;
    }

    public static String getFirstHourOfCurDay() {
        DateTime today = DateTime.now();
        DateTime.Property dayFirstHour = today.hourOfDay();
        return dayFirstHour.withMinimumValue().toString(YYYY_MM_DD_HH);
    }

    public static String getCurHourOfCurDay() {
        DateTime today = DateTime.now().minusHours(1);
        return today.toString(YYYY_MM_DD_HH);
    }

    public static List<String> getLast12Months() {
        DateTime nowYear = DateTime.parse(DateTime.now().toString(YYYY_MM), YYYY_MM);
        List<String> months = new LinkedList<>();
        for (int i = 12; i > 0; i--) {
            months.add(nowYear.toString(YYYY_MM));
            nowYear = nowYear.minusMonths(1);
        }
        return months;
    }

    /**
     * @param yearTime yyyy
     */
    public static List<String> getMonthsOfYear(String yearTime) {
        return getMonthsOfYear(yearTime, DateTime.now());
    }

    /**
     * 查询当前时区月份
     *
     * @param yearTime   yyyy
     * @param timeZoneId 时区ID
     * @return List<String>
     */
    public static List<String> getMonthsOfYear(String yearTime, String timeZoneId) {
        return getMonthsOfYear(yearTime, DateTime.now(DateTimeZone.forID(timeZoneId)));
    }

    public static List<String> getMonthsOfYear(String yearTime, DateTime time) {
        DateTime year = DateTime.parse(yearTime, YYYY).dayOfMonth().withMinimumValue();
        DateTime endYear = DateTime.parse(yearTime, YYYY).plusMonths(12).dayOfMonth().withMinimumValue();
        DateTime nowYear = DateTime.parse(time.toString(YYYY_MM), YYYY_MM);
        List<String> months = new LinkedList<>();
        while ((nowYear.isAfter(year) && endYear.isAfter(year)) || year.equals(nowYear)) {
            months.add(year.toString(YYYY_MM));
            year = year.plusMonths(1);
        }
        return months;
    }


    public static List<String> getDaysOfMonth(String monthTime) {
        return getDaysOfMonth(monthTime, DateTime.now());
    }

    public static List<String> getDaysOfMonth(String monthTime, String timeZoneId) {
        return getDaysOfMonth(monthTime, DateTime.now(DateTimeZone.forID(timeZoneId)));
    }

    public static List<String> getDaysOfMonth(String monthTime, DateTime dateTime) {
        DateTime nowDay = DateTime.parse(dateTime.toString(YYYY_MM_DD), YYYY_MM_DD);
        List<String> days = new LinkedList<>();
        DateTime day = DateTime.parse(monthTime, YYYY_MM).dayOfMonth().withMinimumValue();
        DateTime endDay = DateTime.parse(monthTime, YYYY_MM).plusMonths(1).dayOfMonth().withMinimumValue();
        while ((nowDay.isAfter(day) && day.isBefore(endDay)) || day.equals(nowDay)) {
            days.add(day.toString(YYYY_MM_DD));
            day = day.plusDays(1);
        }
        return days;
    }

    public static List<String> getDaysOfWeek(String weekTime, String timeZoneId) {
        return getDaysOfWeek(weekTime, DateTime.now(DateTimeZone.forID(timeZoneId)));
    }

    public static List<String> getDaysOfWeek(String weekTime, DateTime dateTime) {
        String[] beginEndDay = getWeekDate(weekTime, dateTime.getZone().getID());
        DateTime day = DateTime.parse(beginEndDay[0], YYYY_MM_DD).hourOfDay().withMinimumValue();
        DateTime endDay = DateTime.parse(beginEndDay[1], YYYY_MM_DD).hourOfDay().withMaximumValue();
        DateTime nowDay = DateTime.parse(dateTime.toString(YYYY_MM_DD), YYYY_MM_DD);
        List<String> days = new LinkedList<>();
        while ((nowDay.isAfter(day) && day.isBefore(endDay)) || day.equals(nowDay)) {
            days.add(day.toString(YYYY_MM_DD));
            day = day.plusDays(1);
        }
        return days;
    }

    public static List<String> getHoursOfDay(String dayTime, DateTime dateTime) {
        DateTime day = DateTime.parse(dayTime, YYYY_MM_DD).hourOfDay().withMinimumValue();
        DateTime endDay = DateTime.parse(dayTime, YYYY_MM_DD).plusHours(24);
        DateTime nowDay = DateTime.parse(dateTime.toString(YYYY_MM_DD_HH), YYYY_MM_DD_HH);
        List<String> hours = new LinkedList<>();
        while (nowDay.isAfter(day) && endDay.isAfter(day)) {
            hours.add(day.toString(YYYY_MM_DD_HH));
            day = day.plusHours(1);
        }
        return hours;
    }

    public static List<String> getHoursOfDay(String dayTime, String timeZoneId) {
        return getHoursOfDay(dayTime, DateTime.now(DateTimeZone.forID(timeZoneId)));
    }


    public static List<String> getHoursOfDay(String dayTime) {
        return getHoursOfDay(dayTime, DateTime.now());
    }

    public static boolean onThisYear(String yearTime) {
        return onThisYear(yearTime, DateTime.now());
    }

    public static boolean onThisYear(String yearTime, String timeZoneId) {
        return onThisYear(yearTime, DateTime.now(DateTimeZone.forID(timeZoneId)));
    }

    public static boolean onThisYear(String yearTime, DateTime now) {
        return StringUtils.equals(yearTime, now.toString(YYYY));
    }

    public static boolean onThisMonth(String monthTime) {
        return onThisMonth(monthTime, DateTime.now());
    }

    public static boolean onThisMonth(String monthTime, String timeZoneId) {
        return onThisMonth(monthTime, DateTime.now(DateTimeZone.forID(timeZoneId)));
    }

    public static boolean onThisMonth(String monthTime, DateTime now) {
        return StringUtils.equals(monthTime, now.toString(YYYY_MM));
    }

    public static boolean onThisDay(String dayTime) {
        return StringUtils.equals(dayTime, DateTime.now().toString(YYYY_MM_DD));
    }

    public static boolean onThisDay(String queryTime, DateTime curTime) {
        return StringUtils.equals(queryTime, curTime.toString(YYYY_MM_DD));
    }

    public static boolean onThisDay(String queryTime, String timeZoneId) {
        return StringUtils.equals(queryTime, DateTime.now(DateTimeZone.forID(timeZoneId)).toString(YYYY_MM_DD));
    }

    public static boolean onYesterday(String queryTime, String timeZoneId) {
        return onThisDay(queryTime, DateTime.now(DateTimeZone.forID(timeZoneId)).plusDays(-1));
    }

    public static boolean onTodayOrYesterday(String queryTime, String timeZoneId) {
        return onThisDay(queryTime, timeZoneId) || onYesterday(queryTime, timeZoneId);
    }

    public static boolean isYearTime(String yearTime) {
        try {
            YYYY.parseDateTime(yearTime);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    public static boolean isMonthTime(String monthTime) {
        try {
            YYYY_MM.parseDateTime(monthTime);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    public static boolean isDayTime(String dayTime) {
        try {
            YYYY_MM_DD.parseDateTime(dayTime);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    public static String[] getWeekDate(String dt) {
        return getWeekDate(dt, ZoneId.systemDefault());
    }

    public static String[] getWeekDate(String dt, String zoneId) {
        return getWeekDate(dt, ZoneId.of(zoneId));
    }

    public static String[] getWeekDate(String dt, ZoneId zoneId) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_PATTERN);

        GregorianCalendar cal = GregorianCalendar.from(ZonedDateTime.of(Integer.parseInt(dt.substring(0, 4))
                , Integer.parseInt(dt.substring(4, 6)), Integer.parseInt(dt.substring(6, 8)), 0, 0, 0, 0, ZoneId.systemDefault()));

        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);

        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);

        log.info("getWeekDate time [{}] dt [{}] timeZonId [{}] dayWeek [{}] firstDayOfWeek [{}]", sdf.format(cal.getTime()), dt,
                zoneId.toString(), dayWeek, cal.getFirstDayOfWeek());

        if (dayWeek == Calendar.SUNDAY) {
            // 原本的周日=1，现在=7
            cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - 8);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        } else {
            cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        }

        log.info("getWeekDate start time [{}] dt [{}] timeZonId [{}] dayWeek [{}] firstDayOfWeek [{}]", sdf.format(cal.getTime()), dt,
                zoneId.toString(), dayWeek, cal.getFirstDayOfWeek());

        Date mondayDate = cal.getTime();
        String weekBegin = sdf.format(mondayDate);

        cal.add(Calendar.DATE, 4 + cal.getFirstDayOfWeek());
        Date sundayDate = cal.getTime();
        String weekEnd = sdf.format(sundayDate);

        return new String[] {weekBegin, weekEnd};
    }


    public static String getCurWithZoneId(String deviceTimeZoneId, String pattern) {
        DateTime now = DateTime.now(DateTimeZone.forID(deviceTimeZoneId));
        return now.toString(pattern);
    }
}

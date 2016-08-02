package com.gsmc.joda;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.util.Date;
import java.util.Locale;

/**
 * Created by mars on 2016/5/31.
 */
@Slf4j
public class DateTimeTest {

    final String TIME_PATTERN = "yyyy/MM/dd HH:mm:ss";
    final String DATE_PATTERN = "yyyy/MM/dd";

    @Test
    public void test1() {
        // 使用 java.util.Date取得時間
        log.info("使用 java.util.Date取得時間");
        Date testDate = new Date(System.currentTimeMillis());
        displayTheDate(testDate, "Date Object timestamp is ");
        DateTime time = new DateTime(testDate);
        displayTheDate(time, "DateTime Object timestamp is ");

        // 使用字串指定時間
        log.info("使用字串指定時間");
        time = DateTime.parse("2014-01-03");
        displayTheDate(time);
        // 取得新時間By Formatter
        log.info("取得新時間By Formatter");
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMdd");
        time = formatter.parseDateTime("20130620");

        // 設定時間到指定日期
        log.info("設定時間到2013");
        time = time.withDate(2013, 1, 1);
        log.info("time = " +time.toString(TIME_PATTERN));
        // 設定時間到指定日期+2小時
        log.info("設定時間到2013/1/1+2小時");
        time = time.withDate(2013, 1, 1).plusHours(2);
        displayTheTime(time, "time out by pattern ");
        log.info("設定時間到今年 1/1 +2小時");
        time = time.withDate(2013, 1, 1).plusHours(2);
        displayTheTime(time, "time out by pattern ");

        // 時間比對
        if (time.getMonthOfYear() == 1 && time.getDayOfMonth() == 1) {
            log.info("日期為 1/1");
        }
        DateTime time2 = new DateTime().withDate(2013, 1, 1);
        // 比對兩個日期
        if (time.toLocalDate().isEqual(time2.toLocalDate())) {
            log.info("日期 相同");
        } else {
            log.info("日期不相同");
        }

        // 日期變更為 6/30
        time = time.withMonthOfYear(6).withDayOfYear(30);
        log.info("time out by pattern " + time.toString("yyyyMMdd"));
        if (time.toLocalDate().isEqual(time2.toLocalDate())) {
            log.info("日期 相同");
        } else {
            log.info("日期不相同");
        }

        log.info("取得今天的日期 , 不管時分秒");
        log.info("LocalDate 今天日期為 = " + LocalDate.now().toString(TIME_PATTERN));
        log.info("LocalDateTime今天日期為 = " + LocalDateTime.now().toString(TIME_PATTERN));

        // To Java 原生Date
        log.info("Java 原生Date ：" + time.toDate());

        log.info("測試可變性問題 ：");
        log.info("time ：" + time.toString(DATE_PATTERN));
        time.plusDays(5);
        log.info("After plus 5 days,time ：" + time.toString(DATE_PATTERN));

        log.info("測試可變性問題2 ：");
        log.info("time ：" + time.toString(DATE_PATTERN));
        time = time.plusDays(5);
        log.info("After   time = time.plusDays(5); ：" + time.toString(DATE_PATTERN));

        log.info("取得時間週期 2016-08-01 00:00:00 ~ 2016-08-01 23:59:59");
        LocalDateTime start = LocalDateTime.parse("2016-08-01");
        LocalDateTime end = LocalDateTime.parse("2016-08-01").plusDays(1).minusSeconds(1);
        log.info("時間週期 =  " + start.toString(TIME_PATTERN) + " ~ " + end.toString(TIME_PATTERN));

        log.info("取得時間週期 今天 00:00:00 ~ 今天 23:59:59 ,  現在時間是" + new Date());
        DateTime startTime = DateTime.now().withTimeAtStartOfDay();
        DateTime endTime = DateTime.now().withTimeAtStartOfDay().plusDays(1).minusSeconds(1);
        log.info("時間週期 =  " + startTime.toString(TIME_PATTERN) + " ~ " + endTime.toString(TIME_PATTERN));

        log.info("測試週期的判斷   , use interval");
        Interval interval = new Interval(startTime, endTime);
        log.info("now in interval = " +  interval.containsNow());
        log.info("interval.contains(startTime) = " +  interval.contains(startTime));
        log.info("interval.contains(endTime) = " +  interval.contains(endTime));
        log.info("interval.contains(endTime.plusDays(1)) = " +  interval.contains(endTime.plusDays(1)));

        log.info("endTime > startTime = " + endTime.isAfter(startTime));
        log.info("endTime < startTime = " + endTime.isBefore(startTime));
    }

    private static void displayTheDate(DateTime time) {
        log.info("time = " + time.toString("yyyyMMdd"));
    }

    private static void displayTheDate(DateTime time, String title) {
        log.info(title + time.toString("yyyyMMdd"));
    }

    private static void displayTheTime(DateTime time, String title) {
        log.info(title + time.toString("yyyyMMdd hh:mm:ss"));
    }

    private static void displayTheDate(Date time, String title) {
        log.info(title + time);
    }

}

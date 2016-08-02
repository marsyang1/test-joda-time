package com.gsmc.joda;/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

/**
 * 
 * @author Mars
 */
@Slf4j
public class Mars_App {

  /**
   * @param args the command line arguments
   */
  private static final String pattern = "yyyy年/MM月/dd日";

  public static void main(String[] args) {
    // LocalDate 沒有時間部分 , 也沒有TimeZone
    LocalDate dt = new LocalDate();

    // 1.薪資結算週期：本月1~31日。
    log.info("1.薪資結算週期：" + dt.withDayOfMonth(1).toString(pattern) + " ~ "
        + dt.dayOfMonth().withMaximumValue().toString(pattern));

    // 2.考勤結算週期：上個月26日~本月25日(僅事病假、遲到、忘簽到退)
    log.info("2.考勤結算週期：" + dt.minusMonths(1).withDayOfMonth(26).toString(pattern) + " ~ "
        + dt.withDayOfMonth(25).toString(pattern));

    // 3.發薪日：每月五日，維持不變。
    LocalDate date2 = dt.withDayOfMonth(5);
    if (date2.getDayOfWeek() == DateTimeConstants.SATURDAY
        || date2.getDayOfWeek() == DateTimeConstants.SUNDAY) {
      date2 = date2.plusWeeks(1).withDayOfWeek(1);
    }
    log.info("3.發薪日：" + date2.toString(pattern));

    LocalDate date3 = dt.plusMonths(1).withDayOfMonth(5);
    if (date3.getDayOfWeek() >= 6) {
      date3 = date3.plusWeeks(1).withDayOfWeek(1);
    }
    log.info("4.2014/7月發薪日：" + date3.toString(pattern));

  }
}

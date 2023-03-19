package com.example.capital.common;

import java.util.Calendar;

public class TimeUtils {

  private static final long MILLIS_IN_AN_HOUR = 3600000;
  private static final long MILLIS_IN_A_MINUTE = 60000;
  private static final long MILLIS_IN_A_SECOND = 1000;

  public static String getTimeString(long timeMillis) {
    return timeMillis / MILLIS_IN_AN_HOUR + "h "
        + (timeMillis % MILLIS_IN_AN_HOUR) / MILLIS_IN_A_MINUTE + "m "
        + (timeMillis % MILLIS_IN_A_MINUTE) / MILLIS_IN_A_SECOND + "s";
  }

  public static long getMillisForStartOfCurrentDay() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTimeInMillis();
  }

  public static long getMillisForStartOfCurrentWeek() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.DAY_OF_WEEK, 1);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTimeInMillis();
  }

  public static long getMillisForStartOfCurrentMonth() {
    Calendar calendar = Calendar.getInstance();

    calendar.set(Calendar.DAY_OF_MONTH, 1);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    return calendar.getTimeInMillis();
  }

  public static long getMillisForThreeMonthsAgo() {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MONTH, -3);
    return calendar.getTimeInMillis();
  }

  public static long getMillisForSixMonthsAgo() {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MONTH, -6);
    return calendar.getTimeInMillis();
  }

  public static long getMillisForStartOfCurrentYear() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.MONTH, 0);
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTimeInMillis();
  }

}

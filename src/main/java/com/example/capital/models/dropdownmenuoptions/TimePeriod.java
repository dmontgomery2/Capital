package com.example.capital.models.dropdownmenuoptions;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

import com.example.capital.common.TimeUtils;
import java.util.Map;
import java.util.function.Supplier;

public enum TimePeriod {
  ALL_TIME("all time"),
  TODAY("today"),
  THIS_WEEK("this week"),
  THIS_MONTH("this month"),
  PAST_3_MONTHS("past 3 months"),
  PAST_6_MONTHS("past 6 months"),
  THIS_YEAR("this year");

  private static final Map<TimePeriod, Supplier<Long>> TIME_PERIOD_TO_MILLIS = ofEntries(
      entry(TimePeriod.ALL_TIME, () -> 0L),
      entry(TimePeriod.TODAY, TimeUtils::getMillisForStartOfCurrentDay),
      entry(TimePeriod.THIS_WEEK, TimeUtils::getMillisForStartOfCurrentWeek),
      entry(TimePeriod.THIS_MONTH, TimeUtils::getMillisForStartOfCurrentMonth),
      entry(TimePeriod.PAST_3_MONTHS, TimeUtils::getMillisForThreeMonthsAgo),
      entry(TimePeriod.PAST_6_MONTHS, TimeUtils::getMillisForSixMonthsAgo),
      entry(TimePeriod.THIS_YEAR, TimeUtils::getMillisForStartOfCurrentYear)
  );

  private static final Map<String, TimePeriod> TEXT_TO_SHOW_STATS_FOR = ofEntries(
      entry("all time", ALL_TIME),
      entry("today", TODAY),
      entry("this week", THIS_WEEK),
      entry("this month", THIS_MONTH),
      entry("past 3 months", PAST_3_MONTHS),
      entry("past 6 months", PAST_6_MONTHS),
      entry("this year", THIS_YEAR)
  );
  private final String text;


  TimePeriod(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }

  public static TimePeriod fromText(String text) {
    return TEXT_TO_SHOW_STATS_FOR.get(text);
  }

  public static long getCutoffTimeMillis(TimePeriod timePeriod) {
    return TIME_PERIOD_TO_MILLIS.get(timePeriod).get();
  }
}

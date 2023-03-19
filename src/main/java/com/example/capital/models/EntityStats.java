package com.example.capital.models;

import static com.example.capital.common.Const.ZERO_TIME_STRING;
import static com.example.capital.common.TimeUtils.getTimeString;
import static com.example.capital.common.customcollectors.CustomCollectors.toAnswers;
import static com.example.capital.models.dropdownmenuoptions.TimePeriod.getCutoffTimeMillis;
import static java.util.stream.Collectors.groupingBy;

import com.example.capital.common.TimestampedData;
import com.example.capital.models.dropdownmenuoptions.TimePeriod;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EntityStats implements Serializable {

  private final PoliticalEntity entity;
  private final List<Long> correctTimestamps;
  private final List<Long> answeredTimestamps;

  private final List<TimestampedData<Long>> timesSpent;
  private long timeMillis;

  private final List<TimestampedData<String>> incorrectAnswers;


  public EntityStats(PoliticalEntity entity) {
    this.entity = entity;
    incorrectAnswers = new ArrayList<>();
    correctTimestamps = new ArrayList<>();
    answeredTimestamps = new ArrayList<>();
    timesSpent = new ArrayList<>();
  }

  public void recordCorrect(long timestamp) {
    correctTimestamps.add(timestamp);
    answeredTimestamps.add(timestamp);
  }

  public void recordIncorrect(String answer, long timestamp) {
    answeredTimestamps.add(timestamp);
    incorrectAnswers.add(new TimestampedData<>(answer, timestamp));
  }

//  private boolean hasGivenAnswer(String answer) {
//    return incorrectAnswers.stream().anyMatch(a -> a.getName().equals(answer));
//  }
//
//  private Answer getAnswerForName(String answer) {
//    return incorrectAnswers.stream().filter(a -> a.getName().equals(answer)).findFirst()
//        .orElse(null);
//  }

  public int getPercentageCorrect(TimePeriod timePeriod) {
    int numCorrect = getNumCorrect(timePeriod);
    int total = getNumTotal(timePeriod);

    return (int) (100f * (float) numCorrect / (float) total);
  }

  public int getNumCorrect(TimePeriod timePeriod) {
    return (int) (correctTimestamps.stream()
        .filter(timestamp -> timestamp >= getCutoffTimeMillis(timePeriod))
        .count());
  }


  public int getNumTotal(TimePeriod timePeriod) {
    return (int) (answeredTimestamps.stream()
        .filter(timestamp -> timestamp >= getCutoffTimeMillis(timePeriod))
        .count());
  }

  private long getTimeSpent(TimePeriod timePeriod) {
    return timesSpent.stream()
        .filter(
            timestampedData -> timestampedData.getTimestamp() >= getCutoffTimeMillis(timePeriod))
        .mapToLong(TimestampedData::getData)
        .sum();
  }

  public PoliticalEntity getEntity() {
    return entity;
  }

  public void addTime(long timeElapsed) {
    timesSpent.add(new TimestampedData<>(timeElapsed, System.currentTimeMillis()));
  }

  public String getTotalTimeString(TimePeriod timePeriod) {
    return getTimeString(getTimeSpent(timePeriod));
  }

  public String getAverageTimeString(TimePeriod timePeriod) {
    int numTotal = getNumTotal(timePeriod);
    return numTotal != 0 ? getTimeString(getTimeSpent(timePeriod) / numTotal) : ZERO_TIME_STRING;
  }

  public List<Answer> getIncorrectAnswers(TimePeriod timePeriod) {
    return incorrectAnswers.stream()
        .filter(
            timestampedData -> timestampedData.getTimestamp() >= getCutoffTimeMillis(timePeriod))
        .map(TimestampedData::getData)
        .collect(toAnswers());
  }

  public boolean hasAnsweredInTimePeriod(TimePeriod timePeriod) {
    return answeredTimestamps.stream()
        .anyMatch(timestamp -> timestamp >= TimePeriod.getCutoffTimeMillis(timePeriod));
  }
}

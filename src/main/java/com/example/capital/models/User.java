package com.example.capital.models;

import static com.example.capital.models.dropdownmenuoptions.TimePeriod.getCutoffTimeMillis;
import static java.util.stream.Collectors.toList;

import com.example.capital.common.TimeInterval;
import com.example.capital.common.TimestampedData;
import com.example.capital.common.TimeUtils;
import com.example.capital.daos.PoliticalEntityDao;
import com.example.capital.models.dropdownmenuoptions.TimePeriod;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class User implements Serializable {

  private final String username;
  private final String password;

  private final List<PoliticalEntity> states;
  private final List<PoliticalEntity> countries;
  private Settings settings;

  private final Map<PoliticalEntity, EntityStats> entityToStats;
  private final List<TimestampedData<Integer>> scores;

  private final List<TimeInterval> timesSpent;

  private long lastTimeMillis;

  private final List<Long> quizStartTimestamps;
  private final List<TimestampedData<Integer>> numQuestions;
  private final List<TimestampedData<Integer>> poolSizes;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
    scores = new ArrayList<>();
    states = PoliticalEntityDao.getDefaultStates();
    countries = PoliticalEntityDao.getDefaultCountries();
    entityToStats = new HashMap<>();
    settings = Settings.getDefaultSettings();
    quizStartTimestamps = new ArrayList<>();
    numQuestions = new ArrayList<>();
    poolSizes = new ArrayList<>();
    timesSpent = new ArrayList<>();
  }

  public void setLastTimeMillis(long lastTimeMillis) {
    this.lastTimeMillis = lastTimeMillis;
  }

  public void addTime() {
    long currentTimeMillis = System.currentTimeMillis();
    timesSpent.add(new TimeInterval(lastTimeMillis, currentTimeMillis));
    lastTimeMillis = currentTimeMillis;
  }

  public String getTimeString(TimePeriod timePeriod) {
    return TimeUtils.getTimeString(
        timesSpent.stream()
            .mapToLong(timeInterval -> timeInterval.getMillisAfter(
                TimePeriod.getCutoffTimeMillis(timePeriod)))
            .sum()
    );
  }

  public Optional<EntityStats> getStatsForEntity(PoliticalEntity entity) {
    return Optional.ofNullable(entityToStats.get(entity));
  }

  public boolean hasStatsForEntity(PoliticalEntity entity) {
    boolean isAnsweredBefore = entityToStats.containsKey(entity);
    return isAnsweredBefore;
  }

  public List<EntityStats> getAllStats(TimePeriod timePeriod) {
    return entityToStats.values().stream()
        .filter(stats -> stats.hasAnsweredInTimePeriod(timePeriod))
        .collect(toList());
  }

  public int getPercentageCorrectForEntityName(String entityName, TimePeriod timePeriod) {
    return entityToStats.values()
        .stream()
        .filter(stats -> stats.getEntity().getName().equals(entityName))
        .map(stats -> stats.getPercentageCorrect(timePeriod))
        .findFirst()
        .orElse(0);
  }

  public void recordCorrect(PoliticalEntity politicalEntity, long timestamp) {
    putNewIfNotPresent(politicalEntity);
    entityToStats.get(politicalEntity).recordCorrect(timestamp);
  }

  public void recordIncorrect(PoliticalEntity politicalEntity, String answer, long timestamp) {
    putNewIfNotPresent(politicalEntity);
    entityToStats.get(politicalEntity).recordIncorrect(answer, timestamp);
  }

  private void putNewIfNotPresent(PoliticalEntity politicalEntity) {
    entityToStats.putIfAbsent(politicalEntity, new EntityStats(politicalEntity));
  }

  public boolean matchesUsernameAndPassword(String username, String password) {
    return this.username.equals(username) && this.password.equals(password);
  }

  public boolean matchesUsername(String username) {
    return this.username.equals(username);
  }

  public String getUsername() {
    return username;
  }

  public Settings getSettings() {
    return settings;
  }

  public void setSettings(Settings settings) {
    this.settings = settings;
  }

  public List<PoliticalEntity> getStates() {
    return states;
  }

  public List<PoliticalEntity> getCountries() {
    return countries;
  }

  public void recordScore(int score, long timestamp) {
    scores.add(new TimestampedData<>(score, timestamp));
  }

  public List<Integer> getOverallScores(TimePeriod timePeriod) {
    List<Integer> scoresInPeriod = scores.stream()
        .filter(
            timestampedData -> timestampedData.getTimestamp() >= getCutoffTimeMillis(timePeriod))
        .map(TimestampedData::getData)
        .collect(toList());
    List<Integer> overallScores = new ArrayList<>();
    int numScores = 0;
    int sumScores = 0;
    for (int score : scoresInPeriod) {
      numScores++;
      sumScores += score;
      overallScores.add((int) ((float) sumScores / (float) numScores));
    }
    return overallScores;
  }

  public void addTimeForEntity(PoliticalEntity politicalEntity, long timeElapsed) {
    entityToStats.get(politicalEntity).addTime(timeElapsed);
  }

  public double getNumStateQsAnswred(TimePeriod timePeriod) {
    return getSumAnswered(PoliticalEntity::isState, timePeriod);
  }

  public double getNumCntryQsAnswred(TimePeriod timePeriod) {
    return getSumAnswered(PoliticalEntity::isCountry, timePeriod);
  }

  private double getSumAnswered(Predicate<PoliticalEntity> filter, TimePeriod timePeriod) {
    return entityToStats.keySet().stream()
        .filter(filter)
        .mapToInt(entity -> entityToStats.get(entity).getNumTotal(timePeriod))
        .sum();
  }

  public void addQuiz(long timestamp) {
    quizStartTimestamps.add(timestamp);
  }

  public void addPoolSize(int poolSize, long timestamp) {
    poolSizes.add(new TimestampedData<>(poolSize, timestamp));
  }

  public void addQuizLength(int numberOfQuestions, long timestamp) {
    numQuestions.add(new TimestampedData<>(numberOfQuestions, timestamp));
  }

  public int getAverageQuizLength(TimePeriod timePeriod) {
    int num = numQuestions.stream()
        .filter(tsData -> tsData.getTimestamp() >= getCutoffTimeMillis(timePeriod))
        .mapToInt(TimestampedData::getData)
        .sum();
    int quizzesStarted = (int) (quizStartTimestamps.stream()
        .filter(ts -> ts >= getCutoffTimeMillis(timePeriod))
        .count());
    return quizzesStarted != 0 ? num / quizzesStarted : 0;
  }

  public int getAveragePoolSize(TimePeriod timePeriod) {
    int totalPools = poolSizes.stream()
        .filter(tsData -> tsData.getTimestamp() >= getCutoffTimeMillis(timePeriod))
        .mapToInt(TimestampedData::getData)
        .sum();
    int quizzesStarted = (int) (quizStartTimestamps.stream()
        .filter(ts -> ts >= getCutoffTimeMillis(timePeriod))
        .count());
    return quizzesStarted != 0 ? totalPools / quizzesStarted : 0;
  }
}

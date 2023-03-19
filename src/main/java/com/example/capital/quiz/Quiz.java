package com.example.capital.quiz;

import static java.util.stream.Collectors.toList;

import com.example.capital.daos.PoliticalEntityDao;
import com.example.capital.models.PoliticalEntity;
import com.example.capital.models.PoliticalEntityQuery;
import com.example.capital.models.Question;
import com.example.capital.models.Settings;
import java.util.Collections;
import java.util.List;

public class Quiz {

  private final TimeKeeper timeKeeper;
  private final List<Question> questions;
  private int currentQuestionIndex;

  public Quiz(Settings settings) {
    if (settings.isIncludeStates() && settings.isIncludeCountries()) {
      int numberOfStateQuestions = (int) (
          (float) settings.getNumberOfQuestions() * ((float) settings.getStatesPercentage()) / 100);
      List<PoliticalEntity> statesAndCountries = getStatesAndCountries(settings,
          numberOfStateQuestions);
      Collections.shuffle(statesAndCountries);
      questions = convertPoliticalEntityList(statesAndCountries);
    } else if (settings.isIncludeStates()) {
      questions = convertPoliticalEntityList(
          PoliticalEntityDao.getStates(getStatesOnlyQuery(settings)));
    } else {
      questions = convertPoliticalEntityList(
          PoliticalEntityDao.getCountries(getCountriesOnlyQuery(settings)));
    }
    timeKeeper = new TimeKeeper();
  }

  private List<PoliticalEntity> getStatesAndCountries(Settings settings,
      int numberOfStateQuestions) {
    List<PoliticalEntity> statesAndCountries = PoliticalEntityDao.getStates(
        PoliticalEntityQuery.builder()
            .quantity(numberOfStateQuestions)
            .minDiff(settings.getMinStateDiff())
            .maxDiff(settings.getMaxStateDiff())
            .minCorr(settings.getMinPercentCorrect())
            .timePeriod(settings.getTimePeriod())
            .answeredBefore(settings.getAnsweredBefore())
            .maxCorr(settings.getMaxPercentCorrect())
            .build());
    statesAndCountries.addAll(
        PoliticalEntityDao.getCountries(PoliticalEntityQuery.builder()
            .quantity(settings.getNumberOfQuestions() - numberOfStateQuestions)
            .minDiff(settings.getMinCountryDiff())
            .maxDiff(settings.getMaxCountryDiff())
            .minCorr(settings.getMinPercentCorrect())
            .timePeriod(settings.getTimePeriod())
            .answeredBefore(settings.getAnsweredBefore())
            .maxCorr(settings.getMaxPercentCorrect())
            .build()));
    return statesAndCountries;
  }

  public boolean hasQuestions() {
    return !questions.isEmpty();
  }

  private PoliticalEntityQuery getStatesOnlyQuery(Settings settings) {
    return PoliticalEntityQuery.builder()
        .quantity(settings.getNumberOfQuestions())
        .minDiff(settings.getMinStateDiff())
        .maxDiff(settings.getMaxStateDiff())
        .minCorr(settings.getMinPercentCorrect())
        .timePeriod(settings.getTimePeriod())
        .answeredBefore(settings.getAnsweredBefore())
        .maxCorr(settings.getMaxPercentCorrect())
        .build();
  }

  private PoliticalEntityQuery getCountriesOnlyQuery(Settings settings) {
    return PoliticalEntityQuery.builder()
        .quantity(settings.getNumberOfQuestions())
        .minDiff(settings.getMinCountryDiff())
        .maxDiff(settings.getMaxCountryDiff())
        .minCorr(settings.getMinPercentCorrect())
        .timePeriod(settings.getTimePeriod())
        .answeredBefore(settings.getAnsweredBefore())
        .maxCorr(settings.getMaxPercentCorrect())
        .build();
  }

  private List<Question> convertPoliticalEntityList(List<PoliticalEntity> politicalEntities) {
    return politicalEntities.stream()
        .map(Question::new)
        .collect(toList());
  }

  public int getScore() {
    float numCorrect = questions.stream().filter(Question::getCorrect).count();
    return (int) (100 * numCorrect / ((float) questions.size()));
  }

  public Question getCurrentQuestion() {
    return questions.get(currentQuestionIndex);
  }

  public void advanceQuestion() {
    questions.get(currentQuestionIndex).recordTime(timeKeeper.getTimeElapsed());
    currentQuestionIndex++;
  }

  public boolean finished() {
    return currentQuestionIndex >= questions.size();
  }

}

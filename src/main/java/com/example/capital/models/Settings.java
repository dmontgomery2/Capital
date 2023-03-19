package com.example.capital.models;

import static com.example.capital.models.dropdownmenuoptions.AnsweredBefore.EITHER;

import com.example.capital.models.dropdownmenuoptions.AnsweredBefore;
import com.example.capital.models.dropdownmenuoptions.TimePeriod;
import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Settings implements Serializable {

  private static final int DEFAULT_NUMBER_OF_QUESTIONS = 5;
  private static final boolean DEFAULT_INCLUDE_STATES = true;
  private static final boolean DEFAULT_INCLUDE_COUNTRIES = false;
  private static final int DEFAULT_STATES_PERCENTAGE = 50;
  private static final int DEFAULT_MIN_PERCENT_CORRECT = 0;
  private static final AnsweredBefore DEFAULT_ANSWERED_BEFORE = EITHER;
  private static final int DEFAULT_MAX_PERCENT_CORRECT = 100;
  private static final int DEFAULT_MIN_STATE_DIFF = 1;
  private static final int DEFAULT_MAX_STATE_DIFF = 10;
  private static final int DEFAULT_MIN_COUNTRY_DIFF = 1;
  private static final int DEFAULT_MAX_COUNTRY_DIFF = 10;


  private final int numberOfQuestions;
  private final boolean includeStates;
  private final boolean includeCountries;
  private final int statesPercentage;
  private final int minPercentCorrect;
  private final AnsweredBefore answeredBefore;
  private final TimePeriod timePeriod;
  private final int maxPercentCorrect;
  private final int minStateDiff;
  private final int maxStateDiff;
  private final int minCountryDiff;
  private final int maxCountryDiff;

  public static Settings getDefaultSettings() {
    return Settings.builder()
        .numberOfQuestions(DEFAULT_NUMBER_OF_QUESTIONS)
        .includeStates(DEFAULT_INCLUDE_STATES)
        .includeCountries(DEFAULT_INCLUDE_COUNTRIES)
        .statesPercentage(DEFAULT_STATES_PERCENTAGE)
        .minPercentCorrect(DEFAULT_MIN_PERCENT_CORRECT)
        .answeredBefore(DEFAULT_ANSWERED_BEFORE)
        .maxPercentCorrect(DEFAULT_MAX_PERCENT_CORRECT)
        .minStateDiff(DEFAULT_MIN_STATE_DIFF)
        .maxStateDiff(DEFAULT_MAX_STATE_DIFF)
        .minCountryDiff(DEFAULT_MIN_COUNTRY_DIFF)
        .maxCountryDiff(DEFAULT_MAX_COUNTRY_DIFF)
        .build();
  }

}

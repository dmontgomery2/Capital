package com.example.capital.models;

import com.example.capital.models.dropdownmenuoptions.AnsweredBefore;
import com.example.capital.models.dropdownmenuoptions.TimePeriod;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PoliticalEntityQuery {

  private final int quantity;
  private final int minDiff;
  private final int maxDiff;
  private final int minCorr;
  private final AnsweredBefore answeredBefore;
  private final int maxCorr;
  private final TimePeriod timePeriod;
}

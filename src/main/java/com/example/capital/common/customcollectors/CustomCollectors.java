package com.example.capital.common.customcollectors;

import com.example.capital.models.Answer;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

public class CustomCollectors {

  public static Collector<String, Map<String, Long>, List<Answer>> toAnswers() {
    return AnswersCollector.getInstance();
  }

}

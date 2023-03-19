package com.example.capital.common.customcollectors;

import com.example.capital.models.Answer;
import com.example.capital.models.EntityStats;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

class AnswersCollector implements Collector<String, Map<String, Long>, List<Answer>> {

  private static final AnswersCollector INSTANCE = new AnswersCollector();

  private AnswersCollector() {

  }

  static AnswersCollector getInstance() {
    return INSTANCE;
  }

  @Override
  public Supplier<Map<String, Long>> supplier() {
    return HashMap::new;
  }

  @Override
  public BiConsumer<Map<String, Long>, String> accumulator() {
    return (map, string) -> {
      if (!map.containsKey(string)) {
        map.put(string, 0L);
      }
      map.put(string, map.get(string) + 1);
    };
  }

  @Override
  public BinaryOperator<Map<String, Long>> combiner() {
    return (m1, m2) -> {
      for (String string : m2.keySet()) {
        if (!m1.containsKey(string)) {
          m1.put(string, 0L);
        }
        m1.put(string, m1.get(string) + 1);
      }
      return m1;
    };
  }

  @Override
  public Function<Map<String, Long>, List<Answer>> finisher() {
    return map -> {
      List<Answer> answers = new ArrayList<>();
      for (Map.Entry<String, Long> entry : map.entrySet()) {
        answers.add(new Answer(entry.getKey(), entry.getValue()));
      }
      return answers;
    };
  }

  @Override
  public Set<Characteristics> characteristics() {
    return Collections.emptySet();
  }

}

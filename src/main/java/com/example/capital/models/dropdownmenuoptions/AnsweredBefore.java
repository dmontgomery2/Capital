package com.example.capital.models.dropdownmenuoptions;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

import java.util.Map;

public enum AnsweredBefore {
  YES("yes"),
  NO("no"),
  EITHER("either");

  private static final Map<String, AnsweredBefore> TEXT_TO_ANSWERED_BEFORE = ofEntries(
      entry("yes", YES),
      entry("no", NO),
      entry("either", EITHER)
  );
  private final String text;


  AnsweredBefore(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }

  public static AnsweredBefore fromText(String text) {
    return TEXT_TO_ANSWERED_BEFORE.get(text);
  }
}

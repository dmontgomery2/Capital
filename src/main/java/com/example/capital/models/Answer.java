package com.example.capital.models;

import java.io.Serializable;
import lombok.Getter;

@Getter
public class Answer implements Comparable<Answer>, Serializable {

  private final String name;
  private long quantity;

  public Answer(String name) {
    this.name = name;
  }

  public Answer(String name, long quantity) {
    this.name = name;
    this.quantity = quantity;
  }

  @Override
  public int compareTo(Answer o) {
    if (quantity != o.quantity) {
      return Long.compare(quantity, o.quantity);
    }
    return String.CASE_INSENSITIVE_ORDER.compare(name, o.name);
  }
}

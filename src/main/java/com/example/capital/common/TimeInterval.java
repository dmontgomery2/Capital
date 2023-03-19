package com.example.capital.common;

import java.io.Serializable;

public class TimeInterval implements Serializable {

  private final long startMillis;
  private final long endMillis;

  public TimeInterval(long startMillis, long endMillis) {
    this.startMillis = startMillis;
    this.endMillis = endMillis;
  }

  public long getMillisAfter(long millis) {
    return Math.max(endMillis - Math.max(startMillis, millis), 0);
  }

}

package com.example.capital.quiz;

class TimeKeeper {

  private long lastTimeMillis;

  public TimeKeeper() {
    lastTimeMillis = System.currentTimeMillis();
  }

  public long getTimeElapsed() {
    long currentTime = System.currentTimeMillis();
    long timeElapsed = currentTime - lastTimeMillis;
    lastTimeMillis = currentTime;
    return timeElapsed;
  }

}

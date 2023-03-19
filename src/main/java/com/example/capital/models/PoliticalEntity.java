package com.example.capital.models;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PoliticalEntity implements Serializable {

  private final String name;
  private final String capital;
  private final boolean isState;
  private int difficulty;
  private boolean activated;

  public void setDifficulty(int difficulty) {
    this.difficulty = difficulty;
  }

  public void setActivated(boolean activated) {
    this.activated = activated;
  }

  public boolean isCountry() {
    return !isState;
  }
}

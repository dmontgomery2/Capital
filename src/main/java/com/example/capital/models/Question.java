package com.example.capital.models;

import com.example.capital.daos.UserDao;

public class Question {

  private final PoliticalEntity politicalEntity;
  private final String text;
  private final String correctAnswer;
  private boolean correct;

  public Question(PoliticalEntity politicalEntity) {
    this.politicalEntity = politicalEntity;
    text = "What is the capital of " + politicalEntity.getName() + "?";
    correctAnswer = politicalEntity.getCapital();
  }

  public String getText() {
    return text;
  }

  public String getCorrectAnswer() {
    return correctAnswer;
  }

  public boolean getCorrect() {
    return correct;
  }

  public void setCorrect(boolean correct) {
    this.correct = correct;
  }

  public PoliticalEntity getEntity() {
    return politicalEntity;
  }

  public void recordTime(long timeElapsed) {
    UserDao.getCurrentUser().addTimeForEntity(politicalEntity, timeElapsed);
  }
}

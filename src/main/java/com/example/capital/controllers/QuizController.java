package com.example.capital.controllers;

import static com.example.capital.savedata.DataSaver.saveData;

import com.example.capital.Main;
import com.example.capital.daos.UserDao;
import com.example.capital.quiz.Quiz;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class QuizController {

  private Quiz quiz;
  private boolean submit = true;

  @FXML
  private Label questionText;
  @FXML
  private Button submitButton;
  @FXML
  private TextField answerBox;
  @FXML
  private Label result;


  public void setQuiz(Quiz quiz) {
    this.quiz = quiz;
    questionText.setText(quiz.getCurrentQuestion().getText());
    result.setText("");
  }

  private void goToResults(ActionEvent actionEvent) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("results.fxml"));
    Parent root = fxmlLoader.load();
    ((ResultsController) fxmlLoader.getController()).setScore(quiz.getScore());
    Stage stage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void onSubmit(ActionEvent actionEvent) throws IOException {
    if (submit) {
      if (answerIsCorrect()) {
        onCorrectAnswer();
      } else {
        onIncorrectAnswer();
      }
      UserDao.getCurrentUser().addTime();
      saveData();
      submit = false;
      submitButton.setText("Continue");
      answerBox.setDisable(true);
    } else {
      quiz.advanceQuestion();
      if (quiz.finished()) {
        UserDao.getCurrentUser().recordScore(quiz.getScore(), System.currentTimeMillis());
        saveData();
        goToResults(actionEvent);
        return;
      }
      setUpNewQuestion();
    }
  }

  private void setUpNewQuestion() {
    questionText.setText(quiz.getCurrentQuestion().getText());
    result.setText("");
    submitButton.setText("Submit");
    submit = true;
    answerBox.clear();
    answerBox.setDisable(false);
  }

  private boolean answerIsCorrect() {
    return answerBox.getText().equals(quiz.getCurrentQuestion().getCorrectAnswer());
  }

  private void onCorrectAnswer() {
    UserDao.getCurrentUser()
        .recordCorrect(quiz.getCurrentQuestion().getEntity(), System.currentTimeMillis());
    quiz.getCurrentQuestion().setCorrect(true);
    result.setTextFill(Color.GREEN);
    result.setText("Correct");
  }

  private void onIncorrectAnswer() {
    UserDao.getCurrentUser()
        .recordIncorrect(quiz.getCurrentQuestion().getEntity(), answerBox.getText(),
            System.currentTimeMillis());
    quiz.getCurrentQuestion().setCorrect(false);
    result.setTextFill(Color.RED);
    result.setText(
        "Incorrect.  Correct answer: " + quiz.getCurrentQuestion().getCorrectAnswer());
  }
}
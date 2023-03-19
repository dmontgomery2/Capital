package com.example.capital.controllers;

import com.example.capital.Main;
import com.example.capital.daos.PoliticalEntityDao;
import com.example.capital.daos.UserDao;
import com.example.capital.models.User;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInController {

  private static final String LOGIN_ERROR_MESSAGE = "username or password is incorrect";

  @FXML
  private TextField username;
  @FXML
  private TextField password;
  @FXML
  private Label loginErrorLabel;

  public void logIn(ActionEvent actionEvent) throws IOException {
    Optional<User> optionalUser = UserDao.getByUsernameAndPassword(username.getText(),
        password.getText());
    if (optionalUser.isPresent()) {
      UserDao.setCurrentUser(optionalUser.get());
      UserDao.getCurrentUser().setLastTimeMillis(System.currentTimeMillis());
      PoliticalEntityDao.setCurrentStates(UserDao.getCurrentUser().getStates());
      PoliticalEntityDao.setCurrentCountries(UserDao.getCurrentUser().getCountries());
      FXMLLoader fxmlLoader = new FXMLLoader(
          Main.class.getResource("settings.fxml"));
      Parent root = fxmlLoader.load();
      Stage stage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    } else {
      loginErrorLabel.setText(LOGIN_ERROR_MESSAGE);
    }
  }

  public void createUser(ActionEvent actionEvent) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(
        Main.class.getResource("createUser.fxml"));
    Parent root = fxmlLoader.load();
    Stage stage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }
}

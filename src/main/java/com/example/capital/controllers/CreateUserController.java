package com.example.capital.controllers;

import static com.example.capital.savedata.DataSaver.saveData;

import com.example.capital.Main;
import com.example.capital.daos.UserDao;
import com.example.capital.models.User;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateUserController {

  private static final String EXISTING_USER_ERROR_MESSAGE = "username already exists";
  private static final String PASSWORDS_DO_NOT_MATCH_MESSAGE = "passwords do not match";

  @FXML
  private PasswordField passwordReType;
  @FXML
  private Label passwordErrorLabel;
  @FXML
  private Label passwordErrorLabel2;

  @FXML
  private Label existingUsernameErrorLabel;

  @FXML
  private TextField username;

  @FXML
  private TextField password;

  public void createUser(ActionEvent actionEvent) throws IOException {
    if (UserDao.getByUsername(username.getText()).isPresent()) {
      existingUsernameErrorLabel.setText(EXISTING_USER_ERROR_MESSAGE);
    } else if (!password.getText().equals(passwordReType.getText())) {
      passwordErrorLabel.setText(PASSWORDS_DO_NOT_MATCH_MESSAGE);
      passwordErrorLabel2.setText(PASSWORDS_DO_NOT_MATCH_MESSAGE);
    } else {
      UserDao.addUser(new User(username.getText(), password.getText()));
      saveData();
      goToLoginPage(actionEvent);
    }
  }

  public void cancel(ActionEvent actionEvent) throws IOException {
    goToLoginPage(actionEvent);
  }

  private void goToLoginPage(ActionEvent actionEvent) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(
        Main.class.getResource("login.fxml"));
    Parent root = fxmlLoader.load();
    Stage stage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }
}

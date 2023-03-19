package com.example.capital.controllers;

import static com.example.capital.savedata.DataSaver.saveData;
import static java.util.stream.Collectors.toList;

import com.example.capital.Main;
import com.example.capital.daos.PoliticalEntityDao;
import com.example.capital.daos.UserDao;
import com.example.capital.models.PoliticalEntity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditStateDifficultiesController implements EditController {

  @FXML
  private Label errorLabel;
  @FXML
  private TextField alabama;
  @FXML
  private TextField alaska;
  @FXML
  private TextField arizona;
  @FXML
  private TextField arkansas;
  @FXML
  private TextField california;
  @FXML
  private TextField colorado;
  @FXML
  private TextField connecticut;
  @FXML
  private TextField delaware;
  @FXML
  private TextField florida;
  @FXML
  private TextField georgiaState;
  @FXML
  private TextField hawaii;
  @FXML
  private TextField idaho;
  @FXML
  private TextField illinois;
  @FXML
  private TextField indiana;
  @FXML
  private TextField iowa;
  @FXML
  private TextField kansas;
  @FXML
  private TextField kentucky;
  @FXML
  private TextField louisiana;
  @FXML
  private TextField maine;
  @FXML
  private TextField maryland;
  @FXML
  private TextField massachusetts;
  @FXML
  private TextField michigan;
  @FXML
  private TextField minnesota;
  @FXML
  private TextField mississippi;
  @FXML
  private TextField missouri;
  @FXML
  private TextField montana;
  @FXML
  private TextField nebraska;
  @FXML
  private TextField nevada;
  @FXML
  private TextField newHampshire;
  @FXML
  private TextField newJersey;
  @FXML
  private TextField newMexico;
  @FXML
  private TextField newYork;
  @FXML
  private TextField northCarolina;
  @FXML
  private TextField northDakota;
  @FXML
  private TextField ohio;
  @FXML
  private TextField oklahoma;
  @FXML
  private TextField oregon;
  @FXML
  private TextField pennsylvania;
  @FXML
  private TextField rhodeIsland;
  @FXML
  private TextField southCarolina;
  @FXML
  private TextField southDakota;
  @FXML
  private TextField tennessee;
  @FXML
  private TextField texas;
  @FXML
  private TextField utah;
  @FXML
  private TextField vermont;
  @FXML
  private TextField virginia;
  @FXML
  private TextField washington;
  @FXML
  private TextField westVirginia;
  @FXML
  private TextField wisconsin;
  @FXML
  private TextField wyoming;

  public void onSubmit(ActionEvent actionEvent) throws IOException {
    updateStates();
    if (noErrors()) {
      UserDao.getCurrentUser().addTime();
      saveData();
      goToSettings(actionEvent);
    } else {
      errorLabel.setText("One or more fields had errors.");
    }
  }

  private boolean noErrors() {
    return getTextFields().stream().noneMatch(textField -> textField.getText().equals("ERR"));
  }

  public void onCancel(ActionEvent actionEvent) throws IOException {
    goToSettings(actionEvent);
  }

  private void goToSettings(ActionEvent actionEvent) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("settings.fxml"));
    Parent root = fxmlLoader.load();
    Stage stage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  private void updateStates() {
    getTextFields().forEach(this::updateDifficulty);
  }

  private void updateDifficulty(TextField textField) {
    String text = textField.getText();
    int textAsInt = -1;

    try {
      textAsInt = Integer.parseInt(text);
    } catch (NumberFormatException nfe) {

    }

    if (textAsInt >= 1 && textAsInt <= 10) {
      PoliticalEntityDao.updateStateDifficultyByName(textField.getId(),
          textAsInt);
    } else {
      textField.setText("ERR");
    }
  }

  private List<TextField> getTextFields() {
    List<TextField> textFields = new ArrayList<>();
    textFields.add(alabama);
    textFields.add(alaska);
    textFields.add(arizona);
    textFields.add(arkansas);
    textFields.add(california);
    textFields.add(colorado);
    textFields.add(connecticut);
    textFields.add(delaware);
    textFields.add(florida);
    textFields.add(georgiaState);
    textFields.add(hawaii);
    textFields.add(idaho);
    textFields.add(illinois);
    textFields.add(indiana);
    textFields.add(iowa);
    textFields.add(kansas);
    textFields.add(kentucky);
    textFields.add(louisiana);
    textFields.add(maine);
    textFields.add(maryland);
    textFields.add(massachusetts);
    textFields.add(michigan);
    textFields.add(minnesota);
    textFields.add(mississippi);
    textFields.add(missouri);
    textFields.add(montana);
    textFields.add(nebraska);
    textFields.add(nevada);
    textFields.add(newHampshire);
    textFields.add(newJersey);
    textFields.add(newMexico);
    textFields.add(newYork);
    textFields.add(northCarolina);
    textFields.add(northDakota);
    textFields.add(ohio);
    textFields.add(oklahoma);
    textFields.add(oregon);
    textFields.add(pennsylvania);
    textFields.add(rhodeIsland);
    textFields.add(southCarolina);
    textFields.add(southDakota);
    textFields.add(tennessee);
    textFields.add(texas);
    textFields.add(utah);
    textFields.add(vermont);
    textFields.add(virginia);
    textFields.add(washington);
    textFields.add(westVirginia);
    textFields.add(wisconsin);
    textFields.add(wyoming);
    return textFields;
  }

  public void resetDefaults(ActionEvent actionEvent) {
    getTextFields().forEach(this::setTextDefault);
  }

  public void initialize() {
    getTextFields().forEach(this::setText);
  }

  public void initialize(List<String> textFieldValues) {
    List<TextField> textFields = getTextFields();
    for (int i = 0; i < textFields.size(); i++) {
      textFields.get(i).setText(textFieldValues.get(i));
    }
  }

  private void setText(TextField textField) {
    textField.setText(Integer.toString(
        PoliticalEntityDao.getStateByName(textField.getId()).getDifficulty()));
  }

  private void setTextDefault(TextField textField) {
    textField.setText(
        Integer.toString(PoliticalEntityDao.getDefaultDifficultyForStateName(textField.getId())));
  }

  private void goToState(String name, ActionEvent actionEvent) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(
        Main.class.getResource("individualEntity.fxml"));
    Parent root = fxmlLoader.load();
    IndividualEntityController controller = fxmlLoader.getController();
    controller.setArrivedFromFxml("editStateDifficulties.fxml");
    controller.setEditData(getEditData());
    PoliticalEntity state = PoliticalEntityDao.getStateByName(convertName(name));
    controller.setPoliticalEntity(state);
    Stage stage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  private List<String> getEditData() {
    return getTextFields().stream()
        .map(TextField::getText)
        .collect(toList());
  }

  private String convertName(String name) {
    name = name.replaceAll(" ", "");
    if (name.equals("Georgia")) {
      name = "GeorgiaState";
    }

    return name;
  }

  public void onLinkClick(ActionEvent actionEvent) throws IOException {
    goToState(((Hyperlink) actionEvent.getSource()).getText(), actionEvent);
  }
}

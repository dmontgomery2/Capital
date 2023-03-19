package com.example.capital.controllers;

import static com.example.capital.savedata.DataSaver.saveData;
import static java.util.stream.Collectors.toList;

import com.example.capital.Main;
import com.example.capital.daos.PoliticalEntityDao;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EditStatePoolController implements EditController {

  private static final String ERROR_MESSAGE = "You must select at least one state.";

  @FXML
  private Label errorLabel;

  @FXML
  private CheckBox alabama;
  @FXML
  private CheckBox alaska;
  @FXML
  private CheckBox arizona;
  @FXML
  private CheckBox arkansas;
  @FXML
  private CheckBox california;
  @FXML
  private CheckBox colorado;
  @FXML
  private CheckBox connecticut;
  @FXML
  private CheckBox delaware;
  @FXML
  private CheckBox florida;
  @FXML
  private CheckBox georgiaState;
  @FXML
  private CheckBox hawaii;
  @FXML
  private CheckBox idaho;
  @FXML
  private CheckBox illinois;
  @FXML
  private CheckBox indiana;
  @FXML
  private CheckBox iowa;
  @FXML
  private CheckBox kansas;
  @FXML
  private CheckBox kentucky;
  @FXML
  private CheckBox louisiana;
  @FXML
  private CheckBox maine;
  @FXML
  private CheckBox maryland;
  @FXML
  private CheckBox massachusetts;
  @FXML
  private CheckBox michigan;
  @FXML
  private CheckBox minnesota;
  @FXML
  private CheckBox mississippi;
  @FXML
  private CheckBox missouri;
  @FXML
  private CheckBox montana;
  @FXML
  private CheckBox nebraska;
  @FXML
  private CheckBox nevada;
  @FXML
  private CheckBox newHampshire;
  @FXML
  private CheckBox newJersey;
  @FXML
  private CheckBox newMexico;
  @FXML
  private CheckBox newYork;
  @FXML
  private CheckBox northCarolina;
  @FXML
  private CheckBox northDakota;
  @FXML
  private CheckBox ohio;
  @FXML
  private CheckBox oklahoma;
  @FXML
  private CheckBox oregon;
  @FXML
  private CheckBox pennsylvania;
  @FXML
  private CheckBox rhodeIsland;
  @FXML
  private CheckBox southCarolina;
  @FXML
  private CheckBox southDakota;
  @FXML
  private CheckBox tennessee;
  @FXML
  private CheckBox texas;
  @FXML
  private CheckBox utah;
  @FXML
  private CheckBox vermont;
  @FXML
  private CheckBox virginia;
  @FXML
  private CheckBox washington;
  @FXML
  private CheckBox westVirginia;
  @FXML
  private CheckBox wisconsin;
  @FXML
  private CheckBox wyoming;


  public void onSubmit(ActionEvent actionEvent) throws IOException {
    if (hasErrors()) {
      onUnsuccessfulSubmit();
    } else {
      onSuccessfulSubmit(actionEvent);
    }
  }

  private boolean hasErrors() {
    return getCheckboxes().stream()
        .noneMatch(CheckBox::isSelected);
  }

  private void onUnsuccessfulSubmit() {
    errorLabel.setText(ERROR_MESSAGE);
  }

  private void onSuccessfulSubmit(ActionEvent actionEvent) throws IOException {
    updateStates();
    goToSettings(actionEvent);
  }

  public void onCancel(ActionEvent actionEvent) throws IOException {
    goToSettings(actionEvent);
  }

  private void updateStates() {
    getCheckboxes().forEach(this::updateState);
    saveData();
  }

  private void updateState(CheckBox checkBox) {
    if (checkBox.isSelected()) {
      PoliticalEntityDao.activateState(checkBox.getId());
    } else {
      PoliticalEntityDao.deactivateState(checkBox.getId());
    }
  }

  private void goToSettings(ActionEvent actionEvent)
      throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("settings.fxml"));
    Parent root = fxmlLoader.load();
    Stage stage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  private List<CheckBox> getCheckboxes() {
    List<CheckBox> checkboxes = new ArrayList<>();
    checkboxes.add(alabama);
    checkboxes.add(alaska);
    checkboxes.add(arizona);
    checkboxes.add(arkansas);
    checkboxes.add(california);
    checkboxes.add(colorado);
    checkboxes.add(connecticut);
    checkboxes.add(delaware);
    checkboxes.add(florida);
    checkboxes.add(georgiaState);
    checkboxes.add(hawaii);
    checkboxes.add(idaho);
    checkboxes.add(illinois);
    checkboxes.add(indiana);
    checkboxes.add(iowa);
    checkboxes.add(kansas);
    checkboxes.add(kentucky);
    checkboxes.add(louisiana);
    checkboxes.add(maine);
    checkboxes.add(maryland);
    checkboxes.add(massachusetts);
    checkboxes.add(michigan);
    checkboxes.add(minnesota);
    checkboxes.add(mississippi);
    checkboxes.add(missouri);
    checkboxes.add(montana);
    checkboxes.add(nebraska);
    checkboxes.add(nevada);
    checkboxes.add(newHampshire);
    checkboxes.add(newJersey);
    checkboxes.add(newMexico);
    checkboxes.add(newYork);
    checkboxes.add(northCarolina);
    checkboxes.add(northDakota);
    checkboxes.add(ohio);
    checkboxes.add(oklahoma);
    checkboxes.add(oregon);
    checkboxes.add(pennsylvania);
    checkboxes.add(rhodeIsland);
    checkboxes.add(southCarolina);
    checkboxes.add(southDakota);
    checkboxes.add(tennessee);
    checkboxes.add(texas);
    checkboxes.add(utah);
    checkboxes.add(vermont);
    checkboxes.add(virginia);
    checkboxes.add(washington);
    checkboxes.add(westVirginia);
    checkboxes.add(wisconsin);
    checkboxes.add(wyoming);
    return checkboxes;
  }

  public void selectAll(ActionEvent actionEvent) {
    setAllSelectedTo(true);
  }

  public void deselectAll(ActionEvent actionEvent) {
    setAllSelectedTo(false);
  }

  private void setAllSelectedTo(boolean selected) {
    getCheckboxes().forEach(c -> c.setSelected(selected));
  }

  public void initialize() {
    getCheckboxes().forEach(this::setSelected);
  }


  private void setSelected(CheckBox checkBox) {
    checkBox.setSelected(PoliticalEntityDao.getStateByName(checkBox.getId()).isActivated());
  }

  public void onLinkClick(ActionEvent actionEvent) throws IOException {
    goToState(((Hyperlink) actionEvent.getSource()).getText(), actionEvent);
  }

  private void goToState(String name, ActionEvent actionEvent) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(
        Main.class.getResource("individualEntity.fxml"));
    Parent root = fxmlLoader.load();
    IndividualEntityController controller = fxmlLoader.getController();
    controller.setArrivedFromFxml("editStatePool.fxml");
    controller.setEditData(getEditData());
    PoliticalEntity state = PoliticalEntityDao.getStateByName(convertName(name));
    controller.setPoliticalEntity(state);
    Stage stage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  private List<String> getEditData() {
    return getCheckboxes().stream()
        .map(CheckBox::isSelected)
        .map(Object::toString)
        .collect(toList());
  }

  private String convertName(String name) {
    name = name.replaceAll(" ", "");
    if (name.equals("Georgia")) {
      name = "GeorgiaState";
    }

    return name;
  }

  @Override
  public void initialize(List<String> editData) {
    List<CheckBox> checkboxes = getCheckboxes();
    for (int i = 0; i < checkboxes.size(); i++) {
      checkboxes.get(i).setSelected(Boolean.parseBoolean(editData.get(i)));
    }
  }
}

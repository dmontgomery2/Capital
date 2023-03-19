package com.example.capital.controllers;

import static com.example.capital.common.Const.SHOW_STATS_FOR_LIST;
import static com.example.capital.common.Const.ZERO_TIME_STRING;

import com.example.capital.Main;
import com.example.capital.daos.PoliticalEntityDao;
import com.example.capital.daos.UserDao;
import com.example.capital.models.EntityStats;
import com.example.capital.models.PoliticalEntity;
import com.example.capital.models.dropdownmenuoptions.TimePeriod;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class IndividualEntityController implements Initializable {

  @FXML
  private ChoiceBox showStatsFor;
  @FXML
  private Label entity;
  @FXML
  private Label capital;
  @FXML
  private Label numCorrect;
  @FXML
  private Label numTotal;

  @FXML
  private Label totalTime;

  @FXML
  private Label avgTime;
  @FXML
  private Label difficulty;
  private PoliticalEntity politicalEntity;
  private String arrivedFromFxml;
  private List<String> editData;


  public void setPoliticalEntity(PoliticalEntity politicalEntity) {
    this.politicalEntity = politicalEntity;
    setLabels();
  }

  public void setArrivedFromFxml(String arrivedFromFxml) {
    this.arrivedFromFxml = arrivedFromFxml;
  }

  public void setLabels() {
    if (politicalEntity == null) {
      return;
    }
    entity.setText(politicalEntity.getName());
    capital.setText("Capital: " + politicalEntity.getCapital());
    Optional<EntityStats> stats = UserDao.getCurrentUser().getStatsForEntity(politicalEntity);
    numCorrect.setText("Number answered correctly: " + stats.map(
        s -> s.getNumCorrect(getShowStatsForValueAsTimePeriod())).orElse(0));
    numTotal.setText("Number answered total: " + stats.map(
        s -> s.getNumTotal(getShowStatsForValueAsTimePeriod())).orElse(0));
    totalTime.setText("Total time spent: " + stats.map(
        s -> s.getTotalTimeString(getShowStatsForValueAsTimePeriod())).orElse(ZERO_TIME_STRING));
    avgTime.setText("Average time spent: " + stats.map(
        s -> s.getAverageTimeString(getShowStatsForValueAsTimePeriod())).orElse(ZERO_TIME_STRING));
    difficulty.setText("Difficulty: " + politicalEntity.getDifficulty());
  }

  private TimePeriod getShowStatsForValueAsTimePeriod() {
    return TimePeriod.fromText((String) showStatsFor.getValue());
  }

  public void back(ActionEvent actionEvent) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(arrivedFromFxml));
    Parent root = fxmlLoader.load();
    ((EditController) fxmlLoader.getController()).initialize(editData);
    Stage stage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void setEditData(List<String> editData) {
    this.editData = editData;
  }

  public void goToIncorrectAnswers(ActionEvent actionEvent) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(
        Main.class.getResource("incorrectAnswers.fxml"));
    Parent root = fxmlLoader.load();
    IncorrectAnswersController incorrectAnswersController = fxmlLoader.getController();
    incorrectAnswersController.setEntityStats(
        UserDao.getCurrentUser().getStatsForEntity(politicalEntity)
            .orElse(new EntityStats(politicalEntity)));
    incorrectAnswersController.setArrivedFromFxml(arrivedFromFxml);
    incorrectAnswersController.setEditData(editData);
    Stage stage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void onLeftArrowClick(ActionEvent actionEvent) {
    setPoliticalEntity(PoliticalEntityDao.getPreviousAlphabetically(politicalEntity));
  }

  public void onRightArrowClick(ActionEvent actionEvent) {
    setPoliticalEntity(PoliticalEntityDao.getNextAlphabetically(politicalEntity));
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    showStatsFor.valueProperty()
        .addListener((observableValue, o, t1) -> setLabels());
    showStatsFor.setItems(SHOW_STATS_FOR_LIST);
    showStatsFor.setValue(TimePeriod.ALL_TIME.getText());
  }

}

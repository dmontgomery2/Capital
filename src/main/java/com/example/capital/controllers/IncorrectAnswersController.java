package com.example.capital.controllers;

import static com.example.capital.common.Const.SHOW_STATS_FOR_LIST;

import com.example.capital.Main;
import com.example.capital.models.EntityStats;
import com.example.capital.models.dropdownmenuoptions.TimePeriod;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class IncorrectAnswersController implements Initializable {

  private EntityStats entityStats;
  private String arrivedFromFxml;
  private List<String> editData;

  @FXML
  private ChoiceBox showStatsFor;
  @FXML
  private Label titleLabel;

  @FXML
  private BarChart chart;
  @FXML
  public CategoryAxis chartX;
  @FXML
  public NumberAxis chartY;

  public void setEntityStats(EntityStats entityStats) {
    this.entityStats = entityStats;
    titleLabel.setText("Incorrect answers for " + entityStats.getEntity().getName());
    setDisplayedData();
  }

  private void setDisplayedData() {
    if (entityStats == null) {
      return;
    }

    XYChart.Series chartSeries = new XYChart.Series<>();

    entityStats.getIncorrectAnswers(TimePeriod.fromText((String) showStatsFor.getValue())).forEach(
        answer -> chartSeries.getData()
            .add(new XYChart.Data<>(answer.getName(), answer.getQuantity())));

    chart.getData().clear();
    chart.getData().addAll(chartSeries);
  }


  public void onBackButtonClick(ActionEvent actionEvent) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(
        Main.class.getResource("individualEntity.fxml"));
    Parent root = fxmlLoader.load();
    IndividualEntityController individualEntityController = fxmlLoader.getController();
    individualEntityController.setPoliticalEntity(entityStats.getEntity());
    individualEntityController.setArrivedFromFxml(arrivedFromFxml);
    individualEntityController.setEditData(editData);
    Stage stage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void setArrivedFromFxml(String arrivedFromFxml) {
    this.arrivedFromFxml = arrivedFromFxml;
  }

  public void setEditData(List<String> editData) {
    this.editData = editData;
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    showStatsFor.valueProperty()
        .addListener((observableValue, o, t1) -> setDisplayedData());
    showStatsFor.setItems(SHOW_STATS_FOR_LIST);
    showStatsFor.setValue(TimePeriod.ALL_TIME.getText());
  }


}

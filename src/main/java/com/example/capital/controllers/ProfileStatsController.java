package com.example.capital.controllers;

import static com.example.capital.common.Const.SHOW_STATS_FOR_LIST;
import static com.example.capital.savedata.DataSaver.saveData;
import static java.util.stream.Collectors.toList;

import com.example.capital.Main;
import com.example.capital.daos.UserDao;
import com.example.capital.models.EntityStats;
import com.example.capital.models.dropdownmenuoptions.TimePeriod;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ProfileStatsController implements Initializable {

  @FXML
  private ChoiceBox showStatsFor;

  @FXML
  private Label avgQuizLength;
  @FXML
  private Label avgPoolSize;


  @FXML
  private PieChart stateCountries;
  @FXML
  private LineChart overallScore;
  @FXML
  private NumberAxis overallScoreX;
  @FXML
  private NumberAxis overallScoreY;
  @FXML
  private BarChart fifteenWorst;
  @FXML
  private CategoryAxis fifteenWorstX;
  @FXML
  private NumberAxis fifteenWorstY;

  @FXML
  private Label timeSpent;

  public void onBackButtonClick(ActionEvent actionEvent) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(
        Main.class.getResource("settings.fxml"));
    Parent root = fxmlLoader.load();
    Stage stage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    showStatsFor.setItems(SHOW_STATS_FOR_LIST);
    showStatsFor.setValue(TimePeriod.ALL_TIME.getText());
    UserDao.getCurrentUser().addTime();
    saveData();
    showStatsFor.valueProperty()
        .addListener((observableValue, o, t1) -> setDisplayedData());
    setDisplayedData();
  }

  private void setDisplayedData() {
    setTextData();
    setChartData();
  }

  private void setTextData() {
    timeSpent.setText("Time spent: " + UserDao.getCurrentUser()
        .getTimeString(TimePeriod.fromText((String) showStatsFor.getValue())));
    avgQuizLength.setText("Average quiz length: " + UserDao.getCurrentUser()
        .getAverageQuizLength(TimePeriod.fromText((String) showStatsFor.getValue())));
    avgPoolSize.setText("Average pool size: " + UserDao.getCurrentUser()
        .getAveragePoolSize(TimePeriod.fromText((String) showStatsFor.getValue())));
  }

  private void setChartData() {
    setStateCountriesData(TimePeriod.fromText((String) showStatsFor.getValue()));
    setFifteenWorstData(TimePeriod.fromText((String) showStatsFor.getValue()));
    setOverallScoreData(TimePeriod.fromText((String) showStatsFor.getValue()));
  }

  private void setOverallScoreData(TimePeriod timePeriod) {
    XYChart.Series scoreSeries = new XYChart.Series<>();

    int numberOfQuizzes = 0;

    List<Integer> overallScores = UserDao.getCurrentUser().getOverallScores(timePeriod);

    for (int score : overallScores) {
      numberOfQuizzes++;
      scoreSeries.getData()
          .add(new XYChart.Data<>(numberOfQuizzes, score));
    }

    overallScoreX.setUpperBound(numberOfQuizzes);

    overallScore.getData().clear();
    overallScore.getData().addAll(scoreSeries);
  }

  private void setFifteenWorstData(TimePeriod timePeriod) {
    List<EntityStats> fifteenWorstList = UserDao.getCurrentUser()
        .getAllStats(timePeriod)
        .stream()
        .sorted(Comparator.comparingInt(e -> e.getPercentageCorrect(timePeriod)))
        .limit(15)
        .collect(toList());

    XYChart.Series series = new XYChart.Series<>();

    for (EntityStats stats : fifteenWorstList) {
      series.getData()
          .add(new XYChart.Data(stats.getEntity().getName(),
              stats.getPercentageCorrect(timePeriod)));
    }

    fifteenWorst.getData().clear();
    fifteenWorst.getData().addAll(series);
  }

  private void setStateCountriesData(TimePeriod timePeriod) {
    ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
        new PieChart.Data("States", UserDao.getCurrentUser().getNumStateQsAnswred(timePeriod)),
        new PieChart.Data("Countries", UserDao.getCurrentUser().getNumCntryQsAnswred(timePeriod))
    );
    stateCountries.setData(pieData);
  }

}

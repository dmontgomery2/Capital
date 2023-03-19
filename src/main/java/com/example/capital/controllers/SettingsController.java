package com.example.capital.controllers;

import static com.example.capital.common.Const.SHOW_STATS_FOR_LIST;
import static com.example.capital.models.dropdownmenuoptions.AnsweredBefore.EITHER;
import static com.example.capital.models.dropdownmenuoptions.AnsweredBefore.NO;
import static com.example.capital.models.dropdownmenuoptions.AnsweredBefore.YES;
import static com.example.capital.savedata.DataSaver.saveData;

import com.example.capital.Main;
import com.example.capital.daos.PoliticalEntityDao;
import com.example.capital.daos.UserDao;
import com.example.capital.models.dropdownmenuoptions.AnsweredBefore;
import com.example.capital.models.Settings;
import com.example.capital.models.User;
import com.example.capital.models.dropdownmenuoptions.TimePeriod;
import com.example.capital.quiz.Quiz;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

public class SettingsController implements Initializable {

  private static final String ERROR_MESSAGE = "These settings resulted in no questions.";
  private static final String NOT_APPLICABLE = "N/A";

  private static final ObservableList<String> ANSWERED_BEFORE_LIST = FXCollections.observableArrayList(
      YES.getText(), NO.getText(),
      EITHER.getText());

  @FXML
  private ChoiceBox useStatsFor;

  @FXML
  private ChoiceBox<String> answeredBefore;
  @FXML
  private Label errorLabel;
  @FXML
  private Label maxPercentCorrectValue;
  @FXML
  private Slider maxPercentCorrect;
  @FXML
  private Label minPercentCorrectValue;
  @FXML
  private Slider minPercentCorrect;
  @FXML
  private Label welcomeLabel;
  @FXML
  private Slider minCountryDiff;
  @FXML
  private Slider minStateDiff;
  @FXML
  private Slider maxStateDiff;
  @FXML
  private Slider maxCountryDiff;
  @FXML
  private Label minStateValue;
  @FXML
  private Label maxStateValue;
  @FXML
  private Label minCountryValue;
  @FXML
  private Label maxCountryValue;
  @FXML
  private CheckBox includeStates;
  @FXML
  private CheckBox includeCountries;
  @FXML
  private Slider numberOfQuestions;
  @FXML
  private Label numberOfQuestionsValue;
  @FXML
  private Slider percentageStates;
  @FXML
  private Label percentageStatesValue;

  public void setValueLabels() {
    enforceSliderConstraints();
    numberOfQuestionsValue.setText(Integer.toString((int) numberOfQuestions.getValue()));
    percentageStatesValue.setText(getPercentageValueLabelText(percentageStates));
    minPercentCorrectValue.setText(getPercentageValueLabelText(minPercentCorrect));
    maxPercentCorrectValue.setText(getPercentageValueLabelText(maxPercentCorrect));
    minStateValue.setText(Integer.toString((int) minStateDiff.getValue()));
    maxStateValue.setText(Integer.toString((int) maxStateDiff.getValue()));
    minCountryValue.setText(Integer.toString((int) minCountryDiff.getValue()));
    maxCountryValue.setText(Integer.toString((int) maxCountryDiff.getValue()));
  }

  private void enforceSliderConstraints() {
    minStateDiff.setValue(Math.min(minStateDiff.getValue(), maxStateDiff.getValue()));
    maxStateDiff.setValue(Math.max(minStateDiff.getValue(), maxStateDiff.getValue()));
    minCountryDiff.setValue(Math.min(minCountryDiff.getValue(), maxCountryDiff.getValue()));
    maxCountryDiff.setValue(Math.max(minCountryDiff.getValue(), maxCountryDiff.getValue()));
    minPercentCorrect.setValue(
        Math.min(minPercentCorrect.getValue(), maxPercentCorrect.getValue()));
    maxPercentCorrect.setValue(
        Math.max(minPercentCorrect.getValue(), maxPercentCorrect.getValue()));

  }

  private String getPercentageValueLabelText(Slider slider) {
    return (int) slider.getValue() + "%";
  }

  public void onStart(ActionEvent actionEvent) throws IOException {
    Settings settings = getSettingsToStore();
    User user = UserDao.getCurrentUser();
    user.setSettings(settings);
    int poolSize = 0;
    if (settings.isIncludeStates()) {
      poolSize += PoliticalEntityDao.getNumberOfActivatedStates();
    }
    if (settings.isIncludeCountries()) {
      poolSize += PoliticalEntityDao.getNumberOfActivatedCountries();
    }
    user.addQuiz(System.currentTimeMillis());
    user.addPoolSize(poolSize, System.currentTimeMillis());
    user.addQuizLength(settings.getNumberOfQuestions(), System.currentTimeMillis());
    user.addTime();
    saveData();
    Quiz quiz = new Quiz(settings);
    if (quiz.hasQuestions()) {
      onSuccessfulStart(actionEvent, settings);
    } else {
      onUnsuccessfulStart();
    }
  }

  private void onSuccessfulStart(ActionEvent actionEvent, Settings settings) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("quiz.fxml"));
    Parent root = fxmlLoader.load();
    ((QuizController) fxmlLoader.getController()).setQuiz(new Quiz(settings));
    Stage stage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  private void onUnsuccessfulStart() {
    errorLabel.setText(ERROR_MESSAGE);
  }

  private Settings getSettingsToStore() {
    return Settings.builder()
        .numberOfQuestions((int) numberOfQuestions.getValue())
        .includeStates(includeStates.isSelected())
        .includeCountries(includeCountries.isSelected())
        .statesPercentage((int) percentageStates.getValue())
        .minPercentCorrect((int) minPercentCorrect.getValue())
        .answeredBefore(AnsweredBefore.fromText(answeredBefore.getValue()))
        .timePeriod(TimePeriod.fromText((String) useStatsFor.getValue()))
        .maxPercentCorrect((int) maxPercentCorrect.getValue())
        .minStateDiff((int) minStateDiff.getValue())
        .maxStateDiff((int) maxStateDiff.getValue())
        .minCountryDiff((int) minCountryDiff.getValue())
        .maxCountryDiff((int) maxCountryDiff.getValue())
        .build();
  }


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    useStatsFor.setItems(SHOW_STATS_FOR_LIST);
    useStatsFor.setValue(TimePeriod.ALL_TIME.getText());
    welcomeLabel.setText("Welcome, " + UserDao.getCurrentUser().getUsername() + "!");
    answeredBefore.setItems(ANSWERED_BEFORE_LIST);
    answeredBefore.setValue(EITHER.getText());
    ChangeListener<Number> sliderChangeListener = (observableValue, number, t1) -> setValueLabels();
    getSliders().forEach(s -> s.valueProperty().addListener(sliderChangeListener));
    ChangeListener<Object> answeredBeforeChangeListener = (observableValue, object, t1) -> enforceAnsweredBeforeNoRule();
    answeredBefore.valueProperty().addListener(answeredBeforeChangeListener);
    minPercentCorrect.valueProperty().addListener(answeredBeforeChangeListener);
    maxPercentCorrect.valueProperty().addListener(answeredBeforeChangeListener);
    setInitialState(UserDao.getCurrentUser().getSettings());
    checkCheckboxRules(null);

  }

  private void enforceAnsweredBeforeNoRule() {
    if (answeredBefore.getValue().equals(NO.getText())) {
      minPercentCorrect.setDisable(true);
      maxPercentCorrect.setDisable(true);
      minPercentCorrectValue.setText(NOT_APPLICABLE);
      maxPercentCorrectValue.setText(NOT_APPLICABLE);
    } else {
      minPercentCorrect.setDisable(false);
      maxPercentCorrect.setDisable(false);
      minPercentCorrectValue.setText(getPercentageValueLabelText(minPercentCorrect));
      maxPercentCorrectValue.setText(getPercentageValueLabelText(maxPercentCorrect));
    }
  }

  private List<Slider> getSliders() {
    return List.of(numberOfQuestions, percentageStates, minPercentCorrect, maxPercentCorrect,
        minStateDiff, maxStateDiff, minCountryDiff, maxCountryDiff);
  }

  private void setInitialState(Settings settings) {
    minCountryDiff.setValue(settings.getMinCountryDiff());
    minStateDiff.setValue(settings.getMinStateDiff());
    maxStateDiff.setValue(settings.getMaxStateDiff());
    maxCountryDiff.setValue(settings.getMaxCountryDiff());
    includeStates.setSelected(settings.isIncludeStates());
    includeCountries.setSelected(settings.isIncludeCountries());
    numberOfQuestions.setValue(settings.getNumberOfQuestions());
    percentageStates.setValue(settings.getStatesPercentage());
    minPercentCorrect.setValue(settings.getMinPercentCorrect());
    answeredBefore.setValue(settings.getAnsweredBefore().getText());
    maxPercentCorrect.setValue(settings.getMaxPercentCorrect());
  }

  public void checkCheckboxRules(ActionEvent actionEvent) {
    checkBothCheckboxesCantBeDeselectedRule();
    checkSlidersActivatedRules();
  }

  private void checkBothCheckboxesCantBeDeselectedRule() {
    if (!includeStates.isSelected() && !includeCountries.isSelected()) {
      includeStates.setSelected(true);
    }
  }

  private void checkSlidersActivatedRules() {
    percentageStates.setDisable(!includeStates.isSelected() || !includeCountries.isSelected());
    minStateDiff.setDisable(!includeStates.isSelected());
    maxStateDiff.setDisable(!includeStates.isSelected());
    minCountryDiff.setDisable(!includeCountries.isSelected());
    maxCountryDiff.setDisable(!includeCountries.isSelected());
  }

  public void onEditStatePool(ActionEvent actionEvent) throws IOException {
    setSettingsOnCurrentUser();
    FXMLLoader fxmlLoader = new FXMLLoader(
        Main.class.getResource("editStatePool.fxml"));
    Parent root = fxmlLoader.load();
    ((EditStatePoolController) fxmlLoader.getController()).initialize();
    Stage stage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void onEditCountryPool(ActionEvent actionEvent) throws IOException {
    setSettingsOnCurrentUser();
    FXMLLoader fxmlLoader = new FXMLLoader(
        Main.class.getResource("editCountryPool.fxml"));
    Parent root = fxmlLoader.load();
    Stage stage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void editStateDifficulties(ActionEvent actionEvent) throws IOException {
    setSettingsOnCurrentUser();
    FXMLLoader fxmlLoader = new FXMLLoader(
        Main.class.getResource("editStateDifficulties.fxml"));
    Parent root = fxmlLoader.load();
    ((EditStateDifficultiesController) fxmlLoader.getController()).initialize();
    Stage stage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void editCountryDifficulties(ActionEvent actionEvent) throws IOException {
    setSettingsOnCurrentUser();
    FXMLLoader fxmlLoader = new FXMLLoader(
        Main.class.getResource("editCountryDifficulties.fxml"));
    Parent root = fxmlLoader.load();
    Stage stage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  private void setSettingsOnCurrentUser() {
    UserDao.getCurrentUser().setSettings(getSettingsToStore());
  }

  public void logOut(ActionEvent actionEvent) throws IOException {
    setSettingsOnCurrentUser();
    UserDao.getCurrentUser().addTime();
    saveData();
    FXMLLoader fxmlLoader = new FXMLLoader(
        Main.class.getResource("login.fxml"));
    Parent root = fxmlLoader.load();
    Stage stage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void viewProfile(ActionEvent actionEvent) throws IOException {
    setSettingsOnCurrentUser();
    FXMLLoader fxmlLoader = new FXMLLoader(
        Main.class.getResource("profile.fxml"));
    Parent root = fxmlLoader.load();
    Stage stage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }
}

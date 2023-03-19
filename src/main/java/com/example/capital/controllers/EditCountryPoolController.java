package com.example.capital.controllers;

import static com.example.capital.savedata.DataSaver.saveData;
import static java.util.stream.Collectors.toList;

import com.example.capital.Main;
import com.example.capital.daos.PoliticalEntityDao;
import com.example.capital.daos.UserDao;
import com.example.capital.models.PoliticalEntity;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EditCountryPoolController implements Initializable, EditController {

  private static final String ERROR_MESSAGE = "You must select at least one country.";

  @FXML
  private Label errorLabel;
  @FXML
  private CheckBox afghanistan;
  @FXML
  private CheckBox albania;
  @FXML
  private CheckBox algeria;
  @FXML
  private CheckBox andorra;
  @FXML
  private CheckBox angola;
  @FXML
  private CheckBox antiguaAndBarbuda;
  @FXML
  private CheckBox argentina;
  @FXML
  private CheckBox armenia;
  @FXML
  private CheckBox australia;
  @FXML
  private CheckBox austria;
  @FXML
  private CheckBox azerbaijan;
  @FXML
  private CheckBox bahrain;
  @FXML
  private CheckBox theBahamas;
  @FXML
  private CheckBox bangladesh;
  @FXML
  private CheckBox barbados;
  @FXML
  private CheckBox belarus;
  @FXML
  private CheckBox belgium;
  @FXML
  private CheckBox belize;
  @FXML
  private CheckBox benin;
  @FXML
  private CheckBox bhutan;
  @FXML
  private CheckBox bolivia;
  @FXML
  private CheckBox bosniaAndHerzegovina;
  @FXML
  private CheckBox botswana;
  @FXML
  private CheckBox brazil;
  @FXML
  private CheckBox brunei;
  @FXML
  private CheckBox bulgaria;
  @FXML
  private CheckBox burkinaFaso;
  @FXML
  private CheckBox burundi;
  @FXML
  private CheckBox caboVerde;
  @FXML
  private CheckBox cambodia;
  @FXML
  private CheckBox cameroon;
  @FXML
  private CheckBox canada;
  @FXML
  private CheckBox centralAfricanRepublic;
  @FXML
  private CheckBox chad;
  @FXML
  private CheckBox chile;
  @FXML
  private CheckBox china;
  @FXML
  private CheckBox colombia;
  @FXML
  private CheckBox comoros;
  @FXML
  private CheckBox democraticRepublicOfTheCongo;
  @FXML
  private CheckBox dominica;
  @FXML
  private CheckBox republicOfTheCongo;
  @FXML
  private CheckBox costaRica;
  @FXML
  private CheckBox coteDIvoire;
  @FXML
  private CheckBox croatia;
  @FXML
  private CheckBox cuba;
  @FXML
  private CheckBox cyprus;
  @FXML
  private CheckBox czechRepublic;
  @FXML
  private CheckBox denmark;
  @FXML
  private CheckBox djibouti;
  @FXML
  private CheckBox ecuador;
  @FXML
  private CheckBox dominicanRepublic;
  @FXML
  private CheckBox egypt;
  @FXML
  private CheckBox elSalvador;
  @FXML
  private CheckBox equatorialGuinea;
  @FXML
  private CheckBox eritrea;
  @FXML
  private CheckBox estonia;
  @FXML
  private CheckBox eswatini;
  @FXML
  private CheckBox ethiopia;
  @FXML
  private CheckBox fiji;
  @FXML
  private CheckBox finland;
  @FXML
  private CheckBox france;
  @FXML
  private CheckBox gabon;
  @FXML
  private CheckBox theGambia;
  @FXML
  private CheckBox georgia;
  @FXML
  private CheckBox germany;
  @FXML
  private CheckBox ghana;
  @FXML
  private CheckBox greece;
  @FXML
  private CheckBox grenada;
  @FXML
  private CheckBox guatemala;
  @FXML
  private CheckBox guinea;
  @FXML
  private CheckBox guineaBissau;
  @FXML
  private CheckBox guyana;
  @FXML
  private CheckBox haiti;
  @FXML
  private CheckBox honduras;
  @FXML
  private CheckBox hungary;
  @FXML
  private CheckBox iceland;
  @FXML
  private CheckBox india;
  @FXML
  private CheckBox indonesia;
  @FXML
  private CheckBox iran;
  @FXML
  private CheckBox iraq;
  @FXML
  private CheckBox ireland;
  @FXML
  private CheckBox israel;
  @FXML
  private CheckBox italy;
  @FXML
  private CheckBox jamaica;
  @FXML
  private CheckBox japan;
  @FXML
  private CheckBox jordan;
  @FXML
  private CheckBox kenya;
  @FXML
  private CheckBox kiribati;
  @FXML
  private CheckBox kazakhstan;
  @FXML
  private CheckBox kosovo;
  @FXML
  private CheckBox kuwait;
  @FXML
  private CheckBox kyrgyzstan;
  @FXML
  private CheckBox laos;
  @FXML
  private CheckBox latvia;
  @FXML
  private CheckBox lebanon;
  @FXML
  private CheckBox lesotho;
  @FXML
  private CheckBox liberia;
  @FXML
  private CheckBox libya;
  @FXML
  private CheckBox liechtenstein;
  @FXML
  private CheckBox lithuania;
  @FXML
  private CheckBox luxembourg;
  @FXML
  private CheckBox madagascar;
  @FXML
  private CheckBox malawi;
  @FXML
  private CheckBox malaysia;
  @FXML
  private CheckBox maldives;
  @FXML
  private CheckBox mali;
  @FXML
  private CheckBox malta;
  @FXML
  private CheckBox marshallIslands;
  @FXML
  private CheckBox mauritania;
  @FXML
  private CheckBox mauritius;
  @FXML
  private CheckBox mexico;
  @FXML
  private CheckBox federatedStatesOfMicronesia;
  @FXML
  private CheckBox moldova;
  @FXML
  private CheckBox monaco;
  @FXML
  private CheckBox mongolia;
  @FXML
  private CheckBox montenegro;
  @FXML
  private CheckBox morocco;
  @FXML
  private CheckBox mozambique;
  @FXML
  private CheckBox myanmar;
  @FXML
  private CheckBox namibia;
  @FXML
  private CheckBox nauru;
  @FXML
  private CheckBox nepal;
  @FXML
  private CheckBox netherlands;
  @FXML
  private CheckBox newZealand;
  @FXML
  private CheckBox nicaragua;
  @FXML
  private CheckBox niger;
  @FXML
  private CheckBox northKorea;
  @FXML
  private CheckBox nigeria;
  @FXML
  private CheckBox northMacedonia;
  @FXML
  private CheckBox norway;
  @FXML
  private CheckBox oman;
  @FXML
  private CheckBox pakistan;
  @FXML
  private CheckBox palau;
  @FXML
  private CheckBox palestine;
  @FXML
  private CheckBox panama;
  @FXML
  private CheckBox papuaNewGuinea;
  @FXML
  private CheckBox paraguay;
  @FXML
  private CheckBox peru;
  @FXML
  private CheckBox philippines;
  @FXML
  private CheckBox poland;
  @FXML
  private CheckBox portugal;
  @FXML
  private CheckBox qatar;
  @FXML
  private CheckBox romania;
  @FXML
  private CheckBox russia;
  @FXML
  private CheckBox rwanda;
  @FXML
  private CheckBox saintKittsAndNevis;
  @FXML
  private CheckBox saintLucia;
  @FXML
  private CheckBox saintVincentAndTheGrenadines;
  @FXML
  private CheckBox samoa;
  @FXML
  private CheckBox sanMarino;
  @FXML
  private CheckBox saoTomeAndPrincipe;
  @FXML
  private CheckBox saudiArabia;
  @FXML
  private CheckBox senegal;
  @FXML
  private CheckBox serbia;
  @FXML
  private CheckBox seychelles;
  @FXML
  private CheckBox sierraLeone;
  @FXML
  private CheckBox singapore;
  @FXML
  private CheckBox slovakia;
  @FXML
  private CheckBox slovenia;
  @FXML
  private CheckBox solomonIslands;
  @FXML
  private CheckBox somalia;
  @FXML
  private CheckBox southAfrica;
  @FXML
  private CheckBox southKorea;
  @FXML
  private CheckBox southSudan;
  @FXML
  private CheckBox spain;
  @FXML
  private CheckBox sudan;
  @FXML
  private CheckBox sriLanka;
  @FXML
  private CheckBox suriname;
  @FXML
  private CheckBox sweden;
  @FXML
  private CheckBox switzerland;
  @FXML
  private CheckBox syria;
  @FXML
  private CheckBox tajikistan;
  @FXML
  private CheckBox tanzania;
  @FXML
  private CheckBox thailand;
  @FXML
  private CheckBox timorLeste;
  @FXML
  private CheckBox togo;
  @FXML
  private CheckBox tonga;
  @FXML
  private CheckBox trinidadAndTobago;
  @FXML
  private CheckBox tunisia;
  @FXML
  private CheckBox turkiye;
  @FXML
  private CheckBox turkmenistan;
  @FXML
  private CheckBox tuvalu;
  @FXML
  private CheckBox uganda;
  @FXML
  private CheckBox ukraine;
  @FXML
  private CheckBox unitedArabEmirates;
  @FXML
  private CheckBox unitedKingdom;
  @FXML
  private CheckBox unitedStatesOfAmerica;
  @FXML
  private CheckBox uruguay;
  @FXML
  private CheckBox uzbekistan;
  @FXML
  private CheckBox vanuatu;
  @FXML
  private CheckBox vaticanCity;
  @FXML
  private CheckBox venezuela;
  @FXML
  private CheckBox vietnam;
  @FXML
  private CheckBox yemen;
  @FXML
  private CheckBox zambia;
  @FXML
  private CheckBox zimbabwe;

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
    updateCountries();
    goToSettings(actionEvent);
  }

  public void onCancel(ActionEvent actionEvent) throws IOException {
    goToSettings(actionEvent);
  }

  private void updateCountries() {
    getCheckboxes().forEach(this::updateState);
    UserDao.getCurrentUser().addTime();
    saveData();
  }

  private void updateState(CheckBox checkBox) {
    if (checkBox.isSelected()) {
      PoliticalEntityDao.activateCountry(checkBox.getId());
    } else {
      PoliticalEntityDao.deactivateCountry(checkBox.getId());
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
    checkboxes.add(afghanistan);
    checkboxes.add(albania);
    checkboxes.add(algeria);
    checkboxes.add(andorra);
    checkboxes.add(angola);
    checkboxes.add(antiguaAndBarbuda);
    checkboxes.add(argentina);
    checkboxes.add(armenia);
    checkboxes.add(australia);
    checkboxes.add(austria);
    checkboxes.add(azerbaijan);
    checkboxes.add(bahrain);
    checkboxes.add(theBahamas);
    checkboxes.add(bangladesh);
    checkboxes.add(barbados);
    checkboxes.add(belarus);
    checkboxes.add(belgium);
    checkboxes.add(belize);
    checkboxes.add(benin);
    checkboxes.add(bhutan);
    checkboxes.add(bolivia);
    checkboxes.add(bosniaAndHerzegovina);
    checkboxes.add(botswana);
    checkboxes.add(brazil);
    checkboxes.add(brunei);
    checkboxes.add(bulgaria);
    checkboxes.add(burkinaFaso);
    checkboxes.add(burundi);
    checkboxes.add(caboVerde);
    checkboxes.add(cambodia);
    checkboxes.add(cameroon);
    checkboxes.add(canada);
    checkboxes.add(centralAfricanRepublic);
    checkboxes.add(chad);
    checkboxes.add(chile);
    checkboxes.add(china);
    checkboxes.add(colombia);
    checkboxes.add(comoros);
    checkboxes.add(democraticRepublicOfTheCongo);
    checkboxes.add(dominica);
    checkboxes.add(republicOfTheCongo);
    checkboxes.add(costaRica);
    checkboxes.add(coteDIvoire);
    checkboxes.add(croatia);
    checkboxes.add(cuba);
    checkboxes.add(cyprus);
    checkboxes.add(czechRepublic);
    checkboxes.add(denmark);
    checkboxes.add(djibouti);
    checkboxes.add(ecuador);
    checkboxes.add(equatorialGuinea);
    checkboxes.add(dominicanRepublic);
    checkboxes.add(egypt);
    checkboxes.add(elSalvador);
    checkboxes.add(eritrea);
    checkboxes.add(estonia);
    checkboxes.add(eswatini);
    checkboxes.add(ethiopia);
    checkboxes.add(fiji);
    checkboxes.add(finland);
    checkboxes.add(france);
    checkboxes.add(gabon);
    checkboxes.add(theGambia);
    checkboxes.add(georgia);
    checkboxes.add(germany);
    checkboxes.add(ghana);
    checkboxes.add(greece);
    checkboxes.add(grenada);
    checkboxes.add(guatemala);
    checkboxes.add(guinea);
    checkboxes.add(guineaBissau);
    checkboxes.add(guyana);
    checkboxes.add(haiti);
    checkboxes.add(honduras);
    checkboxes.add(hungary);
    checkboxes.add(iceland);
    checkboxes.add(india);
    checkboxes.add(indonesia);
    checkboxes.add(iran);
    checkboxes.add(indonesia);
    checkboxes.add(iran);
    checkboxes.add(iraq);
    checkboxes.add(ireland);
    checkboxes.add(israel);
    checkboxes.add(italy);
    checkboxes.add(jamaica);
    checkboxes.add(japan);
    checkboxes.add(jordan);
    checkboxes.add(kenya);
    checkboxes.add(kiribati);
    checkboxes.add(kazakhstan);
    checkboxes.add(kosovo);
    checkboxes.add(kuwait);
    checkboxes.add(kyrgyzstan);
    checkboxes.add(laos);
    checkboxes.add(latvia);
    checkboxes.add(lebanon);
    checkboxes.add(lesotho);
    checkboxes.add(liberia);
    checkboxes.add(libya);
    checkboxes.add(liechtenstein);
    checkboxes.add(lithuania);
    checkboxes.add(luxembourg);
    checkboxes.add(madagascar);
    checkboxes.add(malawi);
    checkboxes.add(malaysia);
    checkboxes.add(maldives);
    checkboxes.add(mali);
    checkboxes.add(malta);
    checkboxes.add(marshallIslands);
    checkboxes.add(mauritania);
    checkboxes.add(mauritius);
    checkboxes.add(mexico);
    checkboxes.add(federatedStatesOfMicronesia);
    checkboxes.add(moldova);
    checkboxes.add(monaco);
    checkboxes.add(mongolia);
    checkboxes.add(montenegro);
    checkboxes.add(morocco);
    checkboxes.add(mozambique);
    checkboxes.add(myanmar);
    checkboxes.add(namibia);
    checkboxes.add(nauru);
    checkboxes.add(nepal);
    checkboxes.add(netherlands);
    checkboxes.add(newZealand);
    checkboxes.add(nicaragua);
    checkboxes.add(niger);
    checkboxes.add(northKorea);
    checkboxes.add(nigeria);
    checkboxes.add(northMacedonia);
    checkboxes.add(norway);
    checkboxes.add(oman);
    checkboxes.add(pakistan);
    checkboxes.add(palau);
    checkboxes.add(palestine);
    checkboxes.add(panama);
    checkboxes.add(papuaNewGuinea);
    checkboxes.add(paraguay);
    checkboxes.add(peru);
    checkboxes.add(philippines);
    checkboxes.add(poland);
    checkboxes.add(portugal);
    checkboxes.add(qatar);
    checkboxes.add(romania);
    checkboxes.add(russia);
    checkboxes.add(rwanda);
    checkboxes.add(saintKittsAndNevis);
    checkboxes.add(saintLucia);
    checkboxes.add(saintVincentAndTheGrenadines);
    checkboxes.add(samoa);
    checkboxes.add(sanMarino);
    checkboxes.add(saoTomeAndPrincipe);
    checkboxes.add(saudiArabia);
    checkboxes.add(senegal);
    checkboxes.add(serbia);
    checkboxes.add(seychelles);
    checkboxes.add(sierraLeone);
    checkboxes.add(singapore);
    checkboxes.add(slovakia);
    checkboxes.add(slovenia);
    checkboxes.add(solomonIslands);
    checkboxes.add(somalia);
    checkboxes.add(southAfrica);
    checkboxes.add(southKorea);
    checkboxes.add(southSudan);
    checkboxes.add(spain);
    checkboxes.add(sudan);
    checkboxes.add(sriLanka);
    checkboxes.add(suriname);
    checkboxes.add(sweden);
    checkboxes.add(switzerland);
    checkboxes.add(syria);
    checkboxes.add(tajikistan);
    checkboxes.add(tanzania);
    checkboxes.add(thailand);
    checkboxes.add(timorLeste);
    checkboxes.add(togo);
    checkboxes.add(tonga);
    checkboxes.add(trinidadAndTobago);
    checkboxes.add(tunisia);
    checkboxes.add(turkiye);
    checkboxes.add(turkmenistan);
    checkboxes.add(tuvalu);
    checkboxes.add(uganda);
    checkboxes.add(ukraine);
    checkboxes.add(unitedArabEmirates);
    checkboxes.add(unitedKingdom);
    checkboxes.add(unitedStatesOfAmerica);
    checkboxes.add(uruguay);
    checkboxes.add(uzbekistan);
    checkboxes.add(vanuatu);
    checkboxes.add(vaticanCity);
    checkboxes.add(venezuela);
    checkboxes.add(vietnam);
    checkboxes.add(yemen);
    checkboxes.add(zambia);
    checkboxes.add(zimbabwe);
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

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    getCheckboxes().forEach(this::setSelected);
  }

  private void setSelected(CheckBox checkBox) {
    checkBox.setSelected(PoliticalEntityDao.getCountryByName(checkBox.getId()).isActivated());
  }

  @Override
  public void initialize(List<String> editData) {
    List<CheckBox> checkboxes = getCheckboxes();
    for (int i = 0; i < checkboxes.size(); i++) {
      checkboxes.get(i).setSelected(Boolean.parseBoolean(editData.get(i)));
    }
  }

  public void onLinkClick(ActionEvent actionEvent) throws IOException {
    goToCountry(((Hyperlink) actionEvent.getSource()).getText(), actionEvent);
  }

  private void goToCountry(String name, ActionEvent actionEvent) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(
        Main.class.getResource("individualEntity.fxml"));
    Parent root = fxmlLoader.load();
    IndividualEntityController controller = fxmlLoader.getController();
    controller.setArrivedFromFxml("editCountryPool.fxml");
    controller.setEditData(getEditData());
    PoliticalEntity country = PoliticalEntityDao.getCountryByName(convertName(name));
    controller.setPoliticalEntity(country);
    Stage stage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  private String convertName(String name) {
    return name.replaceAll("[- ']", "")
        .replaceAll("&", "and");
  }


  private List<String> getEditData() {
    return getCheckboxes().stream()
        .map(CheckBox::isSelected)
        .map(Object::toString)
        .collect(toList());
  }
}

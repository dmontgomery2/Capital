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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditCountryDifficultiesController implements Initializable, EditController {


  @FXML
  private Label errorLabel;

  @FXML
  private TextField afghanistan;
  @FXML
  private TextField albania;
  @FXML
  private TextField algeria;
  @FXML
  private TextField andorra;
  @FXML
  private TextField angola;
  @FXML
  private TextField antiguaAndBarbuda;
  @FXML
  private TextField argentina;
  @FXML
  private TextField armenia;
  @FXML
  private TextField australia;
  @FXML
  private TextField austria;
  @FXML
  private TextField azerbaijan;
  @FXML
  private TextField theBahamas;
  @FXML
  private TextField bahrain;
  @FXML
  private TextField bangladesh;
  @FXML
  private TextField barbados;
  @FXML
  private TextField belarus;
  @FXML
  private TextField belgium;
  @FXML
  private TextField belize;
  @FXML
  private TextField benin;
  @FXML
  private TextField bhutan;
  @FXML
  private TextField bolivia;
  @FXML
  private TextField bosniaAndHerzegovina;
  @FXML
  private TextField botswana;
  @FXML
  private TextField brazil;
  @FXML
  private TextField brunei;
  @FXML
  private TextField bulgaria;
  @FXML
  private TextField burkinaFaso;
  @FXML
  private TextField burundi;
  @FXML
  private TextField caboVerde;
  @FXML
  private TextField cambodia;
  @FXML
  private TextField cameroon;
  @FXML
  private TextField canada;
  @FXML
  private TextField centralAfricanRepublic;
  @FXML
  private TextField chad;
  @FXML
  private TextField chile;
  @FXML
  private TextField china;
  @FXML
  private TextField colombia;
  @FXML
  private TextField comoros;
  @FXML
  private TextField democraticRepublicOfTheCongo;
  @FXML
  private TextField dominica;
  @FXML
  private TextField republicOfTheCongo;
  @FXML
  private TextField costaRica;
  @FXML
  private TextField coteDIvoire;
  @FXML
  private TextField croatia;
  @FXML
  private TextField cuba;
  @FXML
  private TextField cyprus;
  @FXML
  private TextField czechRepublic;
  @FXML
  private TextField denmark;
  @FXML
  private TextField djibouti;
  @FXML
  private TextField dominicanRepublic;
  @FXML
  private TextField ecuador;
  @FXML
  private TextField egypt;
  @FXML
  private TextField elSalvador;
  @FXML
  private TextField equatorialGuinea;
  @FXML
  private TextField eritrea;
  @FXML
  private TextField estonia;
  @FXML
  private TextField eswatini;
  @FXML
  private TextField ethiopia;
  @FXML
  private TextField fiji;
  @FXML
  private TextField finland;
  @FXML
  private TextField france;
  @FXML
  private TextField gabon;
  @FXML
  private TextField theGambia;
  @FXML
  private TextField georgia;
  @FXML
  private TextField germany;
  @FXML
  private TextField ghana;
  @FXML
  private TextField greece;
  @FXML
  private TextField grenada;
  @FXML
  private TextField guatemala;
  @FXML
  private TextField guinea;
  @FXML
  private TextField guineaBissau;
  @FXML
  private TextField guyana;
  @FXML
  private TextField haiti;
  @FXML
  private TextField honduras;
  @FXML
  private TextField hungary;
  @FXML
  private TextField iceland;
  @FXML
  private TextField india;
  @FXML
  private TextField indonesia;
  @FXML
  private TextField iran;
  @FXML
  private TextField iraq;
  @FXML
  private TextField ireland;
  @FXML
  private TextField israel;
  @FXML
  private TextField italy;
  @FXML
  private TextField jamaica;
  @FXML
  private TextField japan;
  @FXML
  private TextField jordan;
  @FXML
  private TextField kazakhstan;
  @FXML
  private TextField kenya;
  @FXML
  private TextField kiribati;
  @FXML
  private TextField kosovo;
  @FXML
  private TextField kuwait;
  @FXML
  private TextField kyrgyzstan;
  @FXML
  private TextField laos;
  @FXML
  private TextField latvia;
  @FXML
  private TextField lebanon;
  @FXML
  private TextField lesotho;
  @FXML
  private TextField liberia;
  @FXML
  private TextField libya;
  @FXML
  private TextField liechtenstein;
  @FXML
  private TextField lithuania;
  @FXML
  private TextField luxembourg;
  @FXML
  private TextField madagascar;
  @FXML
  private TextField malawi;
  @FXML
  private TextField malaysia;
  @FXML
  private TextField maldives;
  @FXML
  private TextField mali;
  @FXML
  private TextField malta;
  @FXML
  private TextField marshallIslands;
  @FXML
  private TextField mauritania;
  @FXML
  private TextField mauritius;
  @FXML
  private TextField mexico;
  @FXML
  private TextField federatedStatesOfMicronesia;
  @FXML
  private TextField moldova;
  @FXML
  private TextField monaco;
  @FXML
  private TextField mongolia;
  @FXML
  private TextField montenegro;
  @FXML
  private TextField morocco;
  @FXML
  private TextField mozambique;
  @FXML
  private TextField myanmar;
  @FXML
  private TextField namibia;
  @FXML
  private TextField nauru;
  @FXML
  private TextField nepal;
  @FXML
  private TextField netherlands;
  @FXML
  private TextField newZealand;
  @FXML
  private TextField nicaragua;
  @FXML
  private TextField niger;
  @FXML
  private TextField nigeria;
  @FXML
  private TextField northKorea;
  @FXML
  private TextField northMacedonia;
  @FXML
  private TextField norway;
  @FXML
  private TextField oman;
  @FXML
  private TextField pakistan;
  @FXML
  private TextField palau;
  @FXML
  private TextField palestine;
  @FXML
  private TextField panama;
  @FXML
  private TextField papuaNewGuinea;
  @FXML
  private TextField paraguay;
  @FXML
  private TextField peru;
  @FXML
  private TextField philippines;
  @FXML
  private TextField poland;
  @FXML
  private TextField portugal;
  @FXML
  private TextField qatar;
  @FXML
  private TextField romania;
  @FXML
  private TextField russia;
  @FXML
  private TextField rwanda;
  @FXML
  private TextField saintKittsAndNevis;
  @FXML
  private TextField saintLucia;
  @FXML
  private TextField saintVincentAndTheGrenadines;
  @FXML
  private TextField samoa;
  @FXML
  private TextField sanMarino;
  @FXML
  private TextField saoTomeAndPrincipe;
  @FXML
  private TextField saudiArabia;
  @FXML
  private TextField senegal;
  @FXML
  private TextField serbia;
  @FXML
  private TextField seychelles;
  @FXML
  private TextField sierraLeone;
  @FXML
  private TextField singapore;
  @FXML
  private TextField slovakia;
  @FXML
  private TextField slovenia;
  @FXML
  private TextField solomonIslands;
  @FXML
  private TextField somalia;
  @FXML
  private TextField southAfrica;
  @FXML
  private TextField southKorea;
  @FXML
  private TextField southSudan;
  @FXML
  private TextField spain;
  @FXML
  private TextField sriLanka;
  @FXML
  private TextField sudan;
  @FXML
  private TextField suriname;
  @FXML
  private TextField sweden;
  @FXML
  private TextField switzerland;
  @FXML
  private TextField syria;
  @FXML
  private TextField tajikistan;
  @FXML
  private TextField tanzania;
  @FXML
  private TextField thailand;
  @FXML
  private TextField timorLeste;
  @FXML
  private TextField togo;
  @FXML
  private TextField tonga;
  @FXML
  private TextField trinidadAndTobago;
  @FXML
  private TextField tunisia;
  @FXML
  private TextField turkiye;
  @FXML
  private TextField turkmenistan;
  @FXML
  private TextField tuvalu;
  @FXML
  private TextField uganda;
  @FXML
  private TextField ukraine;
  @FXML
  private TextField unitedArabEmirates;
  @FXML
  private TextField unitedKingdom;
  @FXML
  private TextField unitedStatesOfAmerica;
  @FXML
  private TextField uruguay;
  @FXML
  private TextField uzbekistan;
  @FXML
  private TextField vanuatu;
  @FXML
  private TextField vaticanCity;
  @FXML
  private TextField venezuela;
  @FXML
  private TextField vietnam;
  @FXML
  private TextField yemen;
  @FXML
  private TextField zambia;
  @FXML
  private TextField zimbabwe;

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
    FXMLLoader fxmlLoader = new FXMLLoader(
        Main.class.getResource("settings.fxml"));
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
      PoliticalEntityDao.updateCountryDifficultyByName(textField.getId(),
          textAsInt);
    } else {
      textField.setText("ERR");
    }
  }

  private List<TextField> getTextFields() {
    List<TextField> textFields = new ArrayList<>();
    textFields.add(afghanistan);
    textFields.add(albania);
    textFields.add(algeria);
    textFields.add(andorra);
    textFields.add(angola);
    textFields.add(antiguaAndBarbuda);
    textFields.add(argentina);
    textFields.add(armenia);
    textFields.add(australia);
    textFields.add(austria);
    textFields.add(azerbaijan);
    textFields.add(theBahamas);
    textFields.add(bahrain);
    textFields.add(bangladesh);
    textFields.add(barbados);
    textFields.add(belarus);
    textFields.add(belgium);
    textFields.add(belize);
    textFields.add(benin);
    textFields.add(bhutan);
    textFields.add(bolivia);
    textFields.add(bosniaAndHerzegovina);
    textFields.add(botswana);
    textFields.add(brazil);
    textFields.add(brunei);
    textFields.add(bulgaria);
    textFields.add(burkinaFaso);
    textFields.add(burundi);
    textFields.add(caboVerde);
    textFields.add(cambodia);
    textFields.add(cameroon);
    textFields.add(canada);
    textFields.add(centralAfricanRepublic);
    textFields.add(chad);
    textFields.add(chile);
    textFields.add(china);
    textFields.add(colombia);
    textFields.add(comoros);
    textFields.add(democraticRepublicOfTheCongo);
    textFields.add(dominica);
    textFields.add(republicOfTheCongo);
    textFields.add(costaRica);
    textFields.add(coteDIvoire);
    textFields.add(croatia);
    textFields.add(cuba);
    textFields.add(cyprus);
    textFields.add(czechRepublic);
    textFields.add(denmark);
    textFields.add(djibouti);
    textFields.add(dominicanRepublic);
    textFields.add(ecuador);
    textFields.add(egypt);
    textFields.add(elSalvador);
    textFields.add(equatorialGuinea);
    textFields.add(eritrea);
    textFields.add(estonia);
    textFields.add(eswatini);
    textFields.add(ethiopia);
    textFields.add(fiji);
    textFields.add(finland);
    textFields.add(france);
    textFields.add(gabon);
    textFields.add(theGambia);
    textFields.add(georgia);
    textFields.add(germany);
    textFields.add(ghana);
    textFields.add(greece);
    textFields.add(grenada);
    textFields.add(guatemala);
    textFields.add(guinea);
    textFields.add(guineaBissau);
    textFields.add(guyana);
    textFields.add(haiti);
    textFields.add(honduras);
    textFields.add(hungary);
    textFields.add(iceland);
    textFields.add(india);
    textFields.add(indonesia);
    textFields.add(iran);
    textFields.add(iraq);
    textFields.add(ireland);
    textFields.add(israel);
    textFields.add(italy);
    textFields.add(jamaica);
    textFields.add(japan);
    textFields.add(jordan);
    textFields.add(kazakhstan);
    textFields.add(kenya);
    textFields.add(kiribati);
    textFields.add(kosovo);
    textFields.add(kuwait);
    textFields.add(kyrgyzstan);
    textFields.add(laos);
    textFields.add(latvia);
    textFields.add(lebanon);
    textFields.add(lesotho);
    textFields.add(liberia);
    textFields.add(libya);
    textFields.add(liechtenstein);
    textFields.add(lithuania);
    textFields.add(lithuania);
    textFields.add(luxembourg);
    textFields.add(madagascar);
    textFields.add(malawi);
    textFields.add(malaysia);
    textFields.add(maldives);
    textFields.add(mali);
    textFields.add(malta);
    textFields.add(marshallIslands);
    textFields.add(mauritania);
    textFields.add(mauritius);
    textFields.add(mexico);
    textFields.add(federatedStatesOfMicronesia);
    textFields.add(moldova);
    textFields.add(monaco);
    textFields.add(mongolia);
    textFields.add(montenegro);
    textFields.add(morocco);
    textFields.add(mozambique);
    textFields.add(myanmar);
    textFields.add(namibia);
    textFields.add(nauru);
    textFields.add(nepal);
    textFields.add(netherlands);
    textFields.add(newZealand);
    textFields.add(nicaragua);
    textFields.add(niger);
    textFields.add(nigeria);
    textFields.add(northKorea);
    textFields.add(northMacedonia);
    textFields.add(norway);
    textFields.add(oman);
    textFields.add(pakistan);
    textFields.add(palau);
    textFields.add(palestine);
    textFields.add(panama);
    textFields.add(papuaNewGuinea);
    textFields.add(paraguay);
    textFields.add(peru);
    textFields.add(philippines);
    textFields.add(poland);
    textFields.add(portugal);
    textFields.add(qatar);
    textFields.add(romania);
    textFields.add(russia);
    textFields.add(rwanda);
    textFields.add(saintKittsAndNevis);
    textFields.add(saintLucia);
    textFields.add(saintVincentAndTheGrenadines);
    textFields.add(samoa);
    textFields.add(sanMarino);
    textFields.add(saoTomeAndPrincipe);
    textFields.add(saudiArabia);
    textFields.add(senegal);
    textFields.add(serbia);
    textFields.add(seychelles);
    textFields.add(sierraLeone);
    textFields.add(singapore);
    textFields.add(slovakia);
    textFields.add(slovenia);
    textFields.add(solomonIslands);
    textFields.add(somalia);
    textFields.add(southAfrica);
    textFields.add(southKorea);
    textFields.add(southSudan);
    textFields.add(spain);
    textFields.add(sriLanka);
    textFields.add(sudan);
    textFields.add(suriname);
    textFields.add(sweden);
    textFields.add(switzerland);
    textFields.add(syria);
    textFields.add(tajikistan);
    textFields.add(tanzania);
    textFields.add(thailand);
    textFields.add(timorLeste);
    textFields.add(togo);
    textFields.add(tonga);
    textFields.add(trinidadAndTobago);
    textFields.add(tunisia);
    textFields.add(turkiye);
    textFields.add(turkmenistan);
    textFields.add(tuvalu);
    textFields.add(uganda);
    textFields.add(ukraine);
    textFields.add(unitedArabEmirates);
    textFields.add(unitedKingdom);
    textFields.add(unitedStatesOfAmerica);
    textFields.add(uruguay);
    textFields.add(uzbekistan);
    textFields.add(vanuatu);
    textFields.add(vaticanCity);
    textFields.add(venezuela);
    textFields.add(vietnam);
    textFields.add(yemen);
    textFields.add(zambia);
    textFields.add(zimbabwe);
    return textFields;
  }

  public void resetDefaults(ActionEvent actionEvent) {
    getTextFields().forEach(this::setTextDefault);
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    getTextFields().forEach(this::setText);
  }

  private void setText(TextField textField) {
    textField.setText(
        Integer.toString(PoliticalEntityDao.getCountryByName(textField.getId()).getDifficulty()));
  }

  private void setTextDefault(TextField textField) {
    textField.setText(
        Integer.toString(PoliticalEntityDao.getDefaultDifficultyForCountryName(textField.getId())));
  }

  @Override
  public void initialize(List<String> editData) {
    List<TextField> textFields = getTextFields();
    for (int i = 0; i < textFields.size(); i++) {
      textFields.get(i).setText(editData.get(i));
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
    controller.setArrivedFromFxml("editCountryDifficulties.fxml");
    controller.setEditData(getEditData());
    PoliticalEntity country = PoliticalEntityDao.getCountryByName(convertName(name));
    controller.setPoliticalEntity(country);
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
    return getTextFields().stream()
        .map(TextField::getText)
        .collect(toList());
  }
}

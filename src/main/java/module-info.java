module com.example.capital {
  requires javafx.controls;
  requires javafx.fxml;
  requires lombok;

  opens com.example.capital to javafx.fxml;
  exports com.example.capital;
  exports com.example.capital.controllers;
  opens com.example.capital.controllers to javafx.fxml;
}
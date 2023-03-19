package com.example.capital;


import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    scene.setFill(Color.PALEGREEN);
    stage.setTitle("Capital!");
    stage.setScene(scene);
    stage.setResizable(false);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}
package org.example.ica1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatApp extends Application {
  public static void main(String[] args) {
    launch();
  }
  
  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(ChatApp.class.getResource("chat-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    
    stage.setMinWidth(400);
    stage.setMaxWidth(1200);
    stage.setMinHeight(300);
    stage.setMaxHeight(900);
    
    stage.setScene(scene);
    stage.show();
  }
}
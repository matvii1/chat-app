package org.example.ica1;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
//import org.example.ica1.FileParser.ChatMessage;
import javafx.scene.text.TextFlow;
import org.example.ica1.FileParser.FileParser;

import org.example.ica1.FileParser.Message;
//import org.example.ica1.FileParser.ChatMessageCell;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class ChatController {
  @FXML
  private Button uploadButton;
  @FXML
  private VBox vlistView;
  
  @FXML
  void onHelloButtonClick(ActionEvent event) throws FileNotFoundException {
    vlistView.getChildren().clear();
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open Text File");
    FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("Text files (*.txt, *.msg)", "*.txt", "*.msg");
    fileChooser.getExtensionFilters().add(txtFilter);
    
    Stage stage = (Stage) uploadButton.getScene().getWindow();
    File selectedFile = fileChooser.showOpenDialog(stage);
    
    if (selectedFile == null) {
      Text errorMsg = new Text("You did not select any file");
      errorMsg.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
      
      vlistView.getChildren().clear();
      vlistView.getChildren().add(errorMsg);
      
      return;
    }
    
    File file = new File(selectedFile.getAbsolutePath());
    ArrayList<Message> messages = FileParser.parseChatFile(file);
    
    for (int i = 0; i < messages.size(); i++) {
      Message currentMessage = messages.get(i);
      
      
      Text time = new Text("[" + currentMessage.time + "]");
      Text name = new Text(currentMessage.name);
      
      HBox messageContainer = new HBox();
      
      String[] words = currentMessage.message.split("\\s+");
      
      for (String word : words) {
        if (":)".equals(word)) {
          Image image = new Image("smile_happy.gif");
          ImageView imgView = new ImageView();
          
          imgView.setImage(image);
          
          messageContainer.getChildren().addAll(imgView, new Text(" "));
        } else if (":(".equals(word)) {
          Image image = new Image("smile_sad.gif");
          ImageView imgView = new ImageView();
          
          imgView.setImage(image);
          
          messageContainer.getChildren().add(imgView);
        } else {
          Text textNode = new Text(word + " ");
          textNode.setStyle("-fx-font-weight: bold;");
          
          messageContainer.getChildren().addAll(textNode);
        }
      }
      
      // Create a VBox to hold the labels
      VBox vbox = new VBox(0); // Set spacing between labels
      vbox.setPadding(new Insets(5)); // Set padding around the VBox
      vbox.getChildren().addAll(time, name, messageContainer);
      
      vlistView.getChildren().add(vbox);
    }
    
    if (messages.isEmpty()) {
      
      Text errorMsg = new Text("Invalid format");
      errorMsg.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
      
      vlistView.getChildren().clear();
      vlistView.getChildren().add(errorMsg);
    }
  }
}


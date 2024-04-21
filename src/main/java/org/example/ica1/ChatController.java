package org.example.ica1;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.ica1.FileParser.FileParser;
import org.example.ica1.Message.Message;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;

public class ChatController {
  @FXML
  private Button uploadButton;
  @FXML
  private VBox vlistView;
  @FXML
  private Label fileName;
  
  @FXML
  void onHelloButtonClick() {
    vlistView.getChildren().clear();
    
    try {
      File file = OpenFile();
      displayMessages(file);
    } catch (FileNotFoundException err) {
      System.out.println("Error: " + err);
    }
    
  }
  
  File OpenFile() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open Your Chat");
    FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("Text files (*.txt, *.msg)", "*.txt", "*.msg");
    fileChooser.getExtensionFilters().add(txtFilter);
    
    Stage stage = (Stage) uploadButton.getScene().getWindow();
    File selectedFile = fileChooser.showOpenDialog(stage);
    
    if (selectedFile == null) {
      Text errorMsg = new Text("You did not select any file");
      errorMsg.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
      
      vlistView.getChildren().clear();
      vlistView.getChildren().add(errorMsg);
    }
    
    String filePath = selectedFile.getAbsolutePath();
    
    fileName.setText(selectedFile.getName() + "\n" + filePath);
    
    return new File(selectedFile.getAbsolutePath());
  }
  
  void displayMessages(File file) throws FileNotFoundException {
    ArrayList<Message> messages = FileParser.parseChatFile(file);
    
    for (int i = 0; i < messages.size(); i++) {
      Message currentMessage = messages.get(i);
      String previousName = null;
      
      if (i > 0) {
        previousName = messages.get(i - 1).name;
      }
      
      boolean isSameName = Objects.equals(currentMessage.name, previousName);
      String formattedName = isSameName ? "..." : currentMessage.name;
      
      Text time = new Text("[" + currentMessage.time + "] ");
      Text name = new Text(formattedName + ": ");
      
      name.setStyle("-fx-fill: blue;");
      
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
      
      HBox hbox = new HBox(0);
      hbox.setPadding(new Insets(5));
      hbox.getChildren().addAll(time, name, messageContainer);
      
      vlistView.getChildren().add(hbox);
      
    }
  }
}


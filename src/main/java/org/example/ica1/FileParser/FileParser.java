package org.example.ica1.FileParser;

import javafx.scene.control.Alert;
import org.example.ica1.Message.Message;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class FileParser {
  
  public static ArrayList<Message> parseChatFile(File file) throws FileNotFoundException {
    ArrayList<Message> messages = new ArrayList<>();
    Scanner scanner = new Scanner(file);
    
    while (scanner.hasNextLine()) {
      String name = null;
      String time = null;
      String message = null;

//      ORDER does matter!!!
      if (scanner.hasNextLine()) {
        String nextLine = scanner.nextLine();
        String[] arr = nextLine.split(":", 2);
        
        if (!Objects.equals(arr[0], "Time") || arr[1].isEmpty()) {
          Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Time format: " + nextLine);
          alert.show();
          
          return new ArrayList<>();
        } else {
          time = arr[1].trim();
        }
      }
      if (scanner.hasNextLine()) {
        String nextLine = scanner.nextLine();
        String[] arr = nextLine.split(":", 2);
        
        if (!Objects.equals(arr[0], "Name") || arr[1].isEmpty()) {
          Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Name format: " + nextLine);
          alert.show();
          
          return new ArrayList<>();
        } else {
          name = arr[1].trim();
        }
      }
      
      if (scanner.hasNextLine()) {
        String nextLine = scanner.nextLine();
        String[] arr = nextLine.split(":", 2);
        
        if (!Objects.equals(arr[0], "Message")) {
          Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Message format: " + nextLine);
          alert.show();
          
          return new ArrayList<>();
        } else {
          message = arr[1].trim();
        }
      }
      
      if (name != null && message != null && time != null) {
        messages.add(new Message(time, name, message));
        
        if (scanner.hasNextLine()) {
          scanner.nextLine();
        }
      }
    }
    
    scanner.close();
    
    return messages;
  }
  
}

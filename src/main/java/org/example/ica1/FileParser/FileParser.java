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
    Scanner scannerCheck = new Scanner(file);
    Scanner scanner = new Scanner(file);
    
    ArrayList<String> array = new ArrayList<>();
    while (scannerCheck.hasNextLine()) {
      array.add(scannerCheck.nextLine());
    }
    
    if (array.isEmpty() || array.getLast().isEmpty()) {
      Alert alert = new Alert(Alert.AlertType.ERROR, "Error: Last element of the array should be an empty string");
      alert.show();
      
      return new ArrayList<>();
    }
    
    while (scanner.hasNextLine()) {
      String name = null;
      String time = null;
      String message = null;

//      ORDER does matter!!!
      if (scanner.hasNextLine()) {
        String nextLine = scanner.nextLine();
        String[] arrayLine = nextLine.split(":", 2);
        
        if (!Objects.equals(arrayLine[0], "Time") || arrayLine[1].isEmpty()) {
          Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid format for message: " + nextLine);
          alert.show();
          
          return new ArrayList<>();
        } else {
          time = arrayLine[1].trim();
        }
      }
      if (scanner.hasNextLine()) {
        String nextLine = scanner.nextLine();
        String[] arrayLine = nextLine.split(":", 2);
        
        if (!Objects.equals(arrayLine[0], "Name") || arrayLine[1].isEmpty()) {
          Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Name format: " + nextLine);
          alert.show();
          
          return new ArrayList<>();
        } else {
          name = arrayLine[1].trim();
        }
      }
      
      if (scanner.hasNextLine()) {
        String nextLine = scanner.nextLine();
        String[] arrayLine = nextLine.split(":", 2);
        
        if (!Objects.equals(arrayLine[0], "Message")) {
          Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Message format: " + nextLine);
          alert.show();
          
          return new ArrayList<>();
        } else {
          message = arrayLine[1].trim();
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

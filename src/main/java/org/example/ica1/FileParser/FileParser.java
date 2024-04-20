package org.example.ica1.FileParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
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
        
        if (!Objects.equals(arr[0], "Time")) {
          time = null;
        } else {
          time = arr[1].trim();
        }
      }
      if (scanner.hasNextLine()) {
        String nextLine = scanner.nextLine();
        String[] arr = nextLine.split(":", 2);
        
        if (!Objects.equals(arr[0], "Name")) {
          name = null;
        } else {
          name = arr[1].trim();
        }
      }
      if (scanner.hasNextLine()) {
        String nextLine = scanner.nextLine();
        String[] arr = nextLine.split(":", 2);
        
        if (!Objects.equals(arr[0], "Message")) {
          message = null;
        } else {
          message = arr[1].trim();
        }
      }
      
      if (name != null && message != null && time != null) {
        messages.add(new Message(time, name, message));
        
        if (scanner.hasNextLine()) {
          scanner.nextLine();
        }
        
      } else {
        return new ArrayList<>(0);
      }
    }
    
    scanner.close();
    
    return messages;
  }
  
}

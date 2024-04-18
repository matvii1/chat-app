package app.FileParser;

public class Message {
  private String time;
  private String name;
  private String message;
  
  public Message(String time, String name, String message) {
    this.time = time;
    this.name = name;
    this.message = message;
  }
  
  public String toJsonString() {
    return "{\"time\":\"" + time + "\",\"name\":\"" + name + "\",\"message\":\"" + message + "\"}";
  }
}
module org.ica1 {
  requires javafx.controls;
  requires javafx.fxml;
  
  requires org.controlsfx.controls;
  requires com.dlsc.formsfx;
  requires org.kordamp.bootstrapfx.core;
  
  opens org.example.ica1 to javafx.fxml;
  exports org.example.ica1;
}
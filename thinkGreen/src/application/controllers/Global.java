package application.controllers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Global {
	public void back(String fxmlFile, Button btn) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/application/design/" + fxmlFile));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
        
        btn.getScene().getWindow().hide();
	}
}

package application.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FoodController {
	@FXML
    private Button legumesBtn;
    
    @FXML
    private Button viandesBtn;
    
    @FXML
    private Button fruitsBtn;
    
    @FXML
    private Button graisseBtn;
    
    @FXML
    private Button backBtn;
    
    private Global global = new Global();
   
    
    @FXML
    public void legumes() throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/application/design/legumes.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        legumesBtn.getScene().getWindow().hide();
    }
    
    @FXML
    public void viandes() throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/application/design/viandes.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        viandesBtn.getScene().getWindow().hide();

    }
    
    @FXML
    public void fruits() throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/application/design/fruits.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        fruitsBtn.getScene().getWindow().hide();

    }
    
    @FXML
    public void graisse() throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/application/design/graisses.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        graisseBtn.getScene().getWindow().hide();

    }
    
    @FXML
    public void back() throws IOException {
    	global.back("activities.fxml", backBtn);
    }

}

package application.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ActivitiesController {
	

    @FXML
    private Button networksButton;

    @FXML
    private Button transportationButton;

    @FXML
    private Button foodButton;
    
    @FXML
    private Button backBtn;
    
    private Global global = new Global();
    
    @FXML
    public void initialize() {
        networksButton.setOnAction(event -> {
			try {
				electronic();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

        transportationButton.setOnAction(event -> {
			try {
				transportation();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

        foodButton.setOnAction(event -> {
			try {
				getFoodPage();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
    }
    
    public void getFoodPage() throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/application/design/food.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        // Close the current login window if needed
        foodButton.getScene().getWindow().hide();
    }
    
    public void electronic() throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/application/design/electronic.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        // Close the current login window if needed
        networksButton.getScene().getWindow().hide();
    }

    public void transportation() throws IOException {
    	transportationButton.getScene().getWindow().hide();

        // Load the activities scene
        Parent root = FXMLLoader.load(getClass().getResource("/application/design/voiture.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void back() throws IOException {
    	global.back("login.fxml", backBtn);
    }
}

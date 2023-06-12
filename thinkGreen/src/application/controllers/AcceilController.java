package application.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AcceilController implements Initializable{
	@FXML
	private Button adminSigninBtn;

	@FXML
	private Button usersSigninBtn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void adminSignin() throws IOException {
        adminSigninBtn.getScene().getWindow().hide();

		Parent root = FXMLLoader.load(getClass().getResource("/application/design/admin_login.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
	}
	
	public void usersSignin() throws IOException {
		usersSigninBtn.getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("/application/design/login.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
	}
}

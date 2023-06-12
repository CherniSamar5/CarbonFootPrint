package application.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AdminLoginController implements Initializable{
	@FXML
	private TextField usernameField;
	@FXML
	private TextField passwordField;
	@FXML
	private Button loginButton;
	
    @FXML
    private Button backBtn;
    
    private Global global = new Global();
	
	private Connection connect;
	private PreparedStatement statement;
	private ResultSet result;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loginButton.setOnAction(event -> login());
		
	}
	
	public boolean isFieldsEmpty(TextField username, TextField pwd) {
		return username.getText().isEmpty() || pwd.getText().isEmpty();
	}
	
	public void showAlert(AlertType type, String title, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	public void login() {
		String sql = "SELECT * FROM admins WHERE username = ? AND password = ?";
		connect = Database.connectDb();
		
		try {
			statement = connect.prepareStatement(sql);
			statement.setString(1, usernameField.getText());
			statement.setString(2, passwordField.getText());
			
			result = statement.executeQuery();
			
			if (isFieldsEmpty(usernameField, passwordField)) {
				showAlert(AlertType.ERROR, "Error Message", "Please fill all blank fields");
			} else {
				if (result.next()) {
					loginButton.getScene().getWindow().hide();
					Parent root = FXMLLoader.load(getClass().getResource("/application/design/users.fxml"));
					Stage stage = new Stage();
					Scene scene = new Scene(root, 750, 540);
					stage.initStyle(StageStyle.TRANSPARENT);
					stage.setScene(scene);
					stage.show();
				} else {
					showAlert(AlertType.ERROR, "Error Message", "Wrong Username or password");

				}
			}
			
		} catch(Exception exp) {
			exp.printStackTrace();
		}
		
	}
	
	
    @FXML
    public void back() throws IOException {
    	global.back("acceuil.fxml", backBtn);

    }
	

}

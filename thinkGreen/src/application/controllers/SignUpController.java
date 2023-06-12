package application.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SignUpController {
    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button signUpButton;
    
    
    @FXML
    private Button backBtn;
    
    private Global global = new Global();
    
    private Connection connect;
    private PreparedStatement statement;
    private ResultSet result;
    
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

	public boolean isValidEmail(String mail) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(mail);
        
        return matcher.matches();
	}
    
    public boolean isFieldsEmpty(TextField username, TextField email, PasswordField password, PasswordField psswordConfirm) {
    	return (
	    			username.getText().isEmpty() ||
	    			email.getText().isEmpty() ||
	    			password.getText().isEmpty() ||
	    			psswordConfirm.getText().isEmpty()
    			);
    }
    
    private void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void initialize() {
        signUpButton.setOnAction(event -> handleSignUp());

    }

    @FXML
    private void handleSignUp() {
    	String sql = "SELECT * FROM users WHERE username = ?";
		String sql2 = "INSERT INTO users(username, email, password) VALUES(?, ?, ?)";

    	connect = Database.connectDb();
    	
    	try {
    		statement = connect.prepareStatement(sql);
    		
    		statement.setString(1, usernameField.getText());
    		
    		result = statement.executeQuery();
    		
    		if (isFieldsEmpty(usernameField, emailField, passwordField, confirmPasswordField)) {
    			showAlert(AlertType.ERROR, "Error Message", "Please fill all blank fields");
    		} else {
    			if (!passwordField.getText().equals(confirmPasswordField.getText())) {
        			showAlert(AlertType.ERROR, "Error Message", "Passwords Do Not Match");
    			} else {
    				if (isValidEmail(emailField.getText())) {
            			if (result.next()) {
                			showAlert(AlertType.ERROR, "Error Message", "Account Already Exists");
            			} else {
            	    		statement = connect.prepareStatement(sql2);
            	    		
            	    		statement.setString(1, usernameField.getText());
            	    		statement.setString(2, emailField.getText());
            	    		statement.setString(3, passwordField.getText());
            	    		
            	    		statement.executeUpdate();
            	    		
                            signUpButton.getScene().getWindow().hide();

                            // Load the activities scene
                            Parent root = FXMLLoader.load(getClass().getResource("/application/design/activities.fxml"));
                            Stage stage = new Stage();
                            Scene scene = new Scene(root);

                            stage.initStyle(StageStyle.TRANSPARENT);
                            stage.setScene(scene);
                            stage.show();
            			}
        			
    				} else {
    					showAlert(AlertType.ERROR, "Error Message", "Email : [ " + emailField.getText() + " ] Not Valid");
    				}
    			}
    		}
    		
    		
    		
    	}catch(Exception exp) {
    		exp.printStackTrace();
    	}
    }
    
    @FXML
    public void back() throws IOException {
    	global.back("login.fxml", backBtn);
    }
}

package application.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Hyperlink createAccount;

    @FXML
    private TextField usernameField;
    
    @FXML
    private Button backBtn;
    
    private Global global = new Global();

    private Connection connect;
    private PreparedStatement statement;
    private ResultSet result;

    private double x = 0;
    private double y = 0;

    @FXML
    private void initialize() {
        loginButton.setOnAction(event -> handleLoginButton());
        createAccount.setOnAction(event -> handleCreateAccountLink());

    }
    
    @FXML
    private void handleCreateAccountLink() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/application/design/Signup.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();

            // Close the current login window if needed
            loginButton.getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "Page Not Found");
        }
    }

    @FXML
    private void handleLoginButton() {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        connect = Database.connectDb();

        try {
            statement = connect.prepareStatement(sql);

            statement.setString(1, usernameField.getText());
            statement.setString(2, passwordField.getText());

            result = statement.executeQuery();

            if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                showAlert(AlertType.ERROR, "Error Message", "Please fill all blank fields");
            } else {
                if (result.next()) {

                    // Hide the current scene
                    loginButton.getScene().getWindow().hide();

                    // Load the activities scene
                    Parent root = FXMLLoader.load(getClass().getResource("/application/design/activities.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    showAlert(AlertType.ERROR, "Error Message", "Wrong Username or Password");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    @FXML
    public void back() throws IOException {
    	global.back("acceuil.fxml", backBtn);

    }
}

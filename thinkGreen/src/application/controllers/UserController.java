package application.controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class UserController implements Initializable{
    @FXML
    private TableView<User> tableView;
    @FXML
    private TableColumn<User, Long> idColumn;
    @FXML
    private TableColumn<User, String> userNameColumn;
    @FXML
    private TableColumn<User, String> mailColumn;
    @FXML
    private TableColumn<User, String> pwdColumn;
    @FXML
    private TextField idField;
    @FXML
    private TextField userNameField;
    @FXML
    private TextField mailField;
    @FXML
    private PasswordField pwdField;
    
    @FXML
    private Button backBtn;
    
    private Global global = new Global();
    
    private DbController db = new DbController();
    
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		idField.setEditable(false);
		
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        mailColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));
        pwdColumn.setCellValueFactory(new PropertyValueFactory<>("pwd"));
        
        tableView.setItems(FXCollections.observableArrayList(
    			db.getUsers()
    		)
		);
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        	if (newSelection != null) {
                User selectedUser = tableView.getSelectionModel().getSelectedItem();
                idField.setText(selectedUser.getId().toString());
                userNameField.setText(selectedUser.getUserName());
                mailField.setText(selectedUser.getMail());
                pwdField.setText(selectedUser.getPwd());
        	} else {
        		clearFields();
        	}
        });
	}
	
    public boolean isFieldsEmpty(TextField username, TextField email, PasswordField password) {
    	return (
	    			username.getText().isEmpty() ||
	    			email.getText().isEmpty() ||
	    			password.getText().isEmpty()
    			);
    }
	
	public boolean isValidEmail(String mail) {
		// Regular Expression
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(mail);
        
        return matcher.matches();
	}
	
	public void showAlert(AlertType type, String title, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}

    public void addUser() {
        if (!isFieldsEmpty(userNameField, mailField, pwdField)) {
        	String userName = userNameField.getText();
            String mail = mailField.getText();
            String pwd = pwdField.getText();
            User user = new User(userName, mail, pwd);
            if (isValidEmail(mail)) {
                if (idField.getText().equals("")) {
                    User userExists = db.getUserByUsername(userName);

                    if (userExists == null) {
                    	db.addUser(user);
                        user = db.getUserByUsername(userName);
                        tableView.getItems().add(user);
                        tableView.refresh();
                        clearFields();
                    } else {
                    	showAlert(AlertType.WARNING, "Warning Message", "User Already Exists Under That Username [ " + userName + " ]");

                    }
                } else {
                	showAlert(AlertType.WARNING, "Warning Message", "You Are Not In This Moode");
                }
            } else {
            	showAlert(AlertType.ERROR, "Error Message", "Email : [ " + mail + " ] Not Valid");
            }
        } else {
        	showAlert(AlertType.ERROR, "Error Message", "Please fill all blank fields Except The User ID");

        }
    }

    public void updateUser() {
        // Update selected user in the table
        User selectedUser = tableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
        	String id = idField.getText();
			String newUserName = userNameField.getText();
            String newMail = mailField.getText();
            String newPwd = pwdField.getText();
            if (!isFieldsEmpty(userNameField, mailField, pwdField) && isValidEmail(newMail)) {
            	User updatedUser = new User(
            			newUserName,
            			newMail,
            			newPwd
            		);            
	            selectedUser.setUserName(newUserName);
	            selectedUser.setMail(newMail);
	            selectedUser.setPwd(newPwd);
	            db.updateUser(Long.parseLong(id), updatedUser);
	            tableView.refresh();
	            idField.clear();
	            clearFields();
            } else {
            	showAlert(AlertType.ERROR, "Error Meesage", "Please fill all blank fields And Check The Email must be a valid email like this one [test@test.com]");
            }
        }
    }

    public void deleteUser() {
        // Delete selected user from the table
        User selectedUser = tableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            	db.deleteUser(selectedUser.getId());
                tableView.getItems().remove(selectedUser);
                tableView.refresh();
                idField.clear();
                clearFields();

        }
    }
    
    public void exportTableData() throws URISyntaxException {
    	StringBuilder content = new StringBuilder();
        for (User user : tableView.getItems()) {
            content
        		.append("ID: ")
        			.append(user.getId())
        			.append(System.lineSeparator())
        			
                .append("Username: ")
                	.append(user.getUserName())
            		.append(System.lineSeparator())
            		
                .append("Email: ")
                	.append(user.getMail())
                	.append(System.lineSeparator())
                	
                .append("Password: ")
                	.append(user.getPwd()).append(System.lineSeparator())
                    .append(System.lineSeparator());
        }

        try {
            File outputFile = new File("./users.txt");
            FileWriter out = new FileWriter(outputFile);
            out.write(content.toString());
            out.close();
            showAlert(AlertType.INFORMATION, "Success Message", "The Table View Has Been Exported To The user.txt File Successfully !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        userNameField.clear();
        mailField.clear();
        pwdField.clear();
    }
    
    @FXML
    public void back() throws IOException {
    	global.back("admin_login.fxml", backBtn);
    }
}

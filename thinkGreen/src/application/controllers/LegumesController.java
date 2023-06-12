package application.controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class LegumesController implements Initializable{

    @FXML
    private ComboBox<String> typeLegumesComboBox;

    @FXML
    private TextField consommationField;

    @FXML
    private TextField resultTextField;

    @FXML
    private Button calculateButton;

    @FXML
    private Button solutionButton;
    @FXML
    private Button backBtn;
    
    private Global global = new Global();
    
    private List<String> solutions = new ArrayList<>();
    
    // facteurEmission (KG)
    private double facteurEmission = 0;
    private double facteurCorrection = 1.5;

    public void prepareSolutions() {
        solutions.add("Choose locally sourced vegetables to reduce transportation emissions.");
        solutions.add("Opt for organic vegetables to minimize the use of chemical fertilizers and pesticides.");
        solutions.add("Reduce food waste by planning meals and properly storing vegetables.");
        solutions.add("Support sustainable farming practices, such as regenerative agriculture.");
        solutions.add("Incorporate more seasonal vegetables into your diet.");
        solutions.add("Use compost or organic fertilizers to nourish your own vegetable garden.");
        solutions.add("Explore plant-based alternatives and reduce reliance on meat.");
    }
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		prepareSolutions();
		resultTextField.setEditable(false);
		typeLegumesComboBox.setItems(FXCollections.observableArrayList(
				"Potatoes",
				"Tomatoes",
				"Carrots"
			)
		);
		
		typeLegumesComboBox.setValue(typeLegumesComboBox.getItems().get(0));
		
	}
	
	public boolean isFieldsEmpty(TextField consommationField) {
		return consommationField.getText().isEmpty();
	}
	
	public void showAlert(AlertType type, String title, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.setWidth(300);
		alert.setHeight(400);
		alert.showAndWait();
	}


    @FXML
    private void calculate() {
        if (isFieldsEmpty(consommationField)) {
        	showAlert(AlertType.ERROR, "Error Message", "Please fill all blank fields");
        } else {
        	try {
        		Float consommation = Float.parseFloat(consommationField.getText());
        		if (typeLegumesComboBox.getValue().equals("Potatoes")) {
        			facteurEmission = 0.3;
        		}
        		
        		if (typeLegumesComboBox.getValue().equals("Tomatoes")) {
        			facteurEmission = 0.4;
        		}
        		
        		if (typeLegumesComboBox.getValue().equals("Carrots")) {
        			facteurEmission = 0.2;
        		}
        		
        		Double result = consommation * facteurEmission * facteurCorrection;
        		resultTextField.setText(result.toString() + " (G)");
        		
        	} catch(Exception exp) {
        		showAlert(AlertType.ERROR, "Error Message", "Consumption must be valid numbers. Please ensure you enter numeric values");
        	}
        }
    }


    @FXML
    private void solution() {
    	Random random = new Random();
    	String randomSolution = solutions.get(random.nextInt(solutions.size()));

    	showAlert(AlertType.INFORMATION, "Advice Message","To effectively address and mitigate the issue of CO2 emissions, we recommend implementing the following strategy :" + randomSolution);
    }
    
    
    @FXML
    public void back() throws IOException {
    	global.back("food.fxml", backBtn);
    }
}

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

public class GraissesController implements Initializable  {
   
	@FXML
    private ComboBox<String> typeGraissesComboBox;
    
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
    private double facteurCorrection = 1.2;

    public void prepareSolutions() {
    	solutions.add("Choose sustainable and eco-friendly options: Opt for fats produced from sustainable sources and certified organic products.");
    	solutions.add("Support local producers: Purchase locally-produced fats to reduce transportation emissions and support local economies.");
    	solutions.add("Reduce consumption: Use fats in moderation and avoid excessive use in cooking or baking. Opt for healthier cooking methods that require less fat.");
    	solutions.add("Use eco-friendly packaging: Choose fats that come in recyclable or biodegradable packaging materials to minimize waste and environmental impact.");
    	solutions.add("Proper storage and usage: Store fats properly to avoid spoilage and wastage. Use them sparingly and avoid unnecessary wastage.");
    	solutions.add("Recycling and waste management: Dispose of used fats responsibly by recycling or using designated waste management systems to prevent environmental pollution.");
    	solutions.add("Explore plant-based alternatives: Consider using plant-based fats as alternatives to Butter and Oil, such as avocado oil, coconut oil, or plant-based spreads.");
    }
    
  
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		prepareSolutions();
		typeGraissesComboBox.setItems(FXCollections.observableArrayList(
				"Olive oil",
				"Butter"
			)
		);
		
		typeGraissesComboBox.setValue(typeGraissesComboBox.getItems().get(0));
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
        		if (typeGraissesComboBox.getValue().equals("Olive oil")) {
        			facteurEmission = 6.4;
        		}
        		
        		if (typeGraissesComboBox.getValue().equals("Butter")) {
        			facteurEmission = 12;
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

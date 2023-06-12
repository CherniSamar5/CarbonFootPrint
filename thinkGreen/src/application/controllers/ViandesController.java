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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ViandesController implements Initializable{
    @FXML
    private ComboBox<String> typeViendsComboBox;

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
        solutions.add("Reduce meat consumption: Decrease the overall amount of meat consumed by incorporating more plant-based meals into your diet. This can include adopting Meatless Mondays or reducing meat portion sizes.");
        solutions.add("Choose leaner cuts of meat: Opt for leaner cuts of meat as they require less resources for production and have a lower carbon footprint.");
        solutions.add("Support sustainable farming practices: Purchase meat from local, organic, and sustainable farms that prioritize environmental stewardship and animal welfare.");
        solutions.add("Minimize food waste: Plan meals effectively to reduce food waste, as wasted meat contributes to unnecessary carbon emissions.");
        solutions.add("Opt for grass-fed and pasture-raised options: Choose grass-fed and pasture-raised meat, which tend to have a lower carbon footprint compared to conventionally raised meat.");
        solutions.add("Consider alternative protein sources: Explore plant-based protein alternatives such as tofu, tempeh, and legumes to replace or reduce meat consumption.");
        solutions.add("Advocate for policy changes: Support policies and initiatives that promote sustainable agriculture, reduce greenhouse gas emissions from livestock production, and encourage responsible meat consumption.");
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		prepareSolutions();
		resultTextField.setEditable(false);
		typeViendsComboBox.setItems(FXCollections.observableArrayList(
				"Red Meat",
				"Fish",
				"Chicken"
			)
		);
		
		typeViendsComboBox.setValue(typeViendsComboBox.getItems().get(0));
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
        		if (typeViendsComboBox.getValue().equals("Red Meat")) {
        			facteurEmission = 27;
        		}
        		
        		if (typeViendsComboBox.getValue().equals("Chicken")) {
        			facteurEmission = 6.9;
        		}
        		
        		if (typeViendsComboBox.getValue().equals("Fish")) {
        			facteurEmission = 3.2;
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


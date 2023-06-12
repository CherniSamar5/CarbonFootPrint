package application.controllers;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;


public class Voiture {
	  @FXML
	    private ComboBox<String> fuelComboBox;

	    @FXML
	    private Label fuelLabel;

	    @FXML
	    private Label distanceLabel;

	    @FXML
	    private TextField distanceTextField;

	    @FXML
	    private TextField resultTextField;

	    @FXML
	    private TextField consumptionTextField;

	    @FXML
	    private Button calculateButton;
	    
	    @FXML
	    private Button solutionButton;
	    
	    @FXML
	    private Button backBtn;
	    
	    private Global global = new Global();
	    
	    private Float tauxEmission = 0.5F;
	    
	    private List<String> solutions = new ArrayList<>();
	   
	    public void prepareSolutions() {
	    	solutions.add("Transition to renewable energy sources: Use energy sources such as solar, wind, or hydro power to reduce CO2 emissions associated with electricity production.");
	    	solutions.add("Improving energy efficiency: Adopt technologies and practices that reduce energy consumption, such as building insulation, efficient appliances, and LED lighting.");
	    	solutions.add("Promoting sustainable transportation: Encourage the use of public transportation, carpooling, cycling, or walking to reduce CO2 emissions from travel.");
	    	solutions.add("Using low-emission vehicles: Opt for electric, hybrid, or low-emission vehicles to reduce CO2 emissions from the transportation sector.");
	    	solutions.add("Sustainable agricultural practices: Promote sustainable agricultural methods, such as organic farming, crop rotation, and waste management techniques, to reduce CO2 emissions from agriculture.");
	    	solutions.add("Reducing water consumption: Adopt measures to conserve water, such as wastewater reuse, efficient irrigation, and raising awareness about responsible water usage, to reduce the carbon footprint associated with water production and treatment.");
	    	solutions.add("Reforestation and forest conservation: Promote tree planting and forest preservation, as trees absorb CO2 from the atmosphere and contribute to carbon sequestration.");
	    	solutions.add("Awareness and education: Inform and educate individuals about climate change issues, carbon footprint, and the actions they can take in their daily lives to reduce their environmental impact.");
	    	solutions.add("Carbon offsetting: Support carbon offset projects, such as funding reforestation, renewable energy, or carbon capture and storage projects, to offset residual CO2 emissions.");
	    }

	    @FXML
	    private void initialize() {
	    	prepareSolutions();
	    	solutionButton.setOnAction(event -> solutionButtonClicked());
	    	resultTextField.setEditable(false);
	    	calculateButton.setOnAction(event -> calculateButtonClicked());
	        fuelComboBox.setItems(FXCollections.observableArrayList(
	        			"Diesel",
	        			"Essence",
	        			"Gaz naturel",
	        			"Électricité"
	        		));
	        fuelComboBox.setValue(fuelComboBox.getItems().get(0));
	    }
	    
	    public boolean isFieldsEmpty(TextField distanceTextField, TextField consumptionTextField) {
	    	return distanceTextField.getText().isEmpty() || consumptionTextField.getText().isEmpty();
	    }
	    
	    public void showAlert(AlertType type, String title, String content) {
	    	Alert alert = new Alert(type);
	    	alert.setTitle(title);
	    	alert.setHeaderText(null);
	    	alert.setContentText(content);
	    	alert.showAndWait();
	    }

	    @FXML
	    private void calculateButtonClicked() {
	        if (isFieldsEmpty(distanceTextField, consumptionTextField)) {
	        	showAlert(AlertType.ERROR, "Error Message", "Please fill all blank fields");
	        } else {
	        	try {
	        		Float distance = Float.parseFloat(distanceTextField.getText());
	        		Integer consommation = Integer.parseInt(consumptionTextField.getText());
	        		
	        		if (fuelComboBox.getValue().equals("Diesel")) {
	        			tauxEmission = 2.68F;
	        		}
	        		
	        		if (fuelComboBox.getValue().equals("Essence")) {
	        			tauxEmission = 2.31F;
	        		}
	        		
	        		if (fuelComboBox.getValue().equals("Gaz naturel")) {
	        			tauxEmission = 1.96F;
	        		}
	        		
	        		Float result = consommation * distance * tauxEmission;
	        		
	        		resultTextField.setText(result + " G");
	        	} catch(Exception exp) {
	        		showAlert(AlertType.ERROR, "Error Message", "Distance and KM must be valid numbers. Please ensure you enter numeric values for both fields");
	        	}
	        }
	    }
	    @FXML
	    private void solutionButtonClicked() {
	    	Random random = new Random();
	    	String randomSolution = solutions.get(random.nextInt(solutions.size()));

	    	showAlert(AlertType.INFORMATION, "Advice Message","To effectively address and mitigate the issue of CO2 emissions, we recommend implementing the following strategy :" + randomSolution);
	    }
	    
	    @FXML
	    public void back() throws IOException {
	    	global.back("activities.fxml", backBtn);
	    }

}

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

public class ElectronicController implements Initializable{
    @FXML
    private ComboBox<String> typeElectronicComboBow;

    @FXML
    private TextField duree;

    @FXML
    private Button calculateBtn;

    @FXML
    private TextField footPrintPerDayField;

    @FXML
    private TextField footPrintPerYearField;

    @FXML
    private Button solutionBtn;
    
    @FXML
    private Button backBtn;
    
    private Global global = new Global();
    
    // facteurEmission (KG)
    private double facteurEmission = 0;
    // Energie (KW)
    private double energie = 0;
    
    private List<String> solutions = new ArrayList<>();
    
    public void prepareSolutions() {
    	solutions.add("Energy-efficient devices: Choose electronics that have high energy efficiency ratings. Look for devices with ENERGY STAR certification or similar labels, as they are designed to consume less power and emit fewer greenhouse gases.");
    	solutions.add("Power management settings: Optimize the power management settings on your electronic devices. Enable sleep or standby modes when the devices are not in use, and adjust the settings to ensure they automatically power down or enter low-power modes after a certain period of inactivity.");
    	solutions.add("Unplug unused devices: Unplug electronic devices when they are not in use. Many devices continue to consume a small amount of energy even when they are turned off or in standby mode. Using power strips with on/off switches can make it easier to disconnect multiple devices at once.");
    	solutions.add("Renewable energy sources: Power your electronics with clean energy sources such as solar power or wind energy. Install solar panels on your property or consider subscribing to a renewable energy program provided by your utility company.");
    	solutions.add("Proper disposal and recycling: Dispose of electronic devices responsibly by recycling them. Electronics contain valuable materials that can be reused, and proper recycling prevents harmful substances from polluting the environment. Look for authorized electronic recycling facilities in your area.");
    	solutions.add("Extended product lifespan: Make your electronics last longer by taking good care of them. Keep them clean, protect them from physical damage, and follow manufacturer guidelines for maintenance and usage. Extending the lifespan of electronics reduces the need for frequent replacements, which helps reduce overall CO2 emissions.");
    	solutions.add("Second-hand electronics: Consider buying used or refurbished electronics instead of always purchasing new ones. This reduces the demand for new manufacturing and decreases the environmental impact associated with the production of new devices.");
    	solutions.add("Minimalistic approach: Assess your actual needs before buying new electronics. Avoid unnecessary purchases and choose multi-functional devices that can perform multiple tasks. By adopting a minimalistic approach, you can reduce the number of devices you own and subsequently reduce energy consumption and CO2 emissions.");
    	solutions.add("Education and awareness: Spread awareness about the environmental impact of electronics and the importance of responsible usage. Educate others about energy-saving practices, proper disposal methods, and the benefits of choosing eco-friendly electronics.");
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		prepareSolutions();
		typeElectronicComboBow.setItems(FXCollections.observableArrayList(
				"Desktop",
				"Laptop",
				"TV LCD",
				"Smartphone",
				"Consol",
				// Climatiseur
				"Air Conditioner"
			)
		);
		
		typeElectronicComboBow.setValue(typeElectronicComboBow.getItems().get(0));
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
        if (isFieldsEmpty(duree)) {
        	showAlert(AlertType.ERROR, "Error Message", "Please fill all blank fields");
        } else {
        	try {
        		Float dureeUtulisation = Float.parseFloat(duree.getText());
        		if (dureeUtulisation > 24) {
        			showAlert(AlertType.ERROR, "Error Message", "Value Of The Duration Of Use Its Per Day So Must Be Less Than Or Equal 24");
        		} else {
        			if (typeElectronicComboBow.getValue().equals("Desktop")) {
            			facteurEmission = 0.2;
            			energie = 0.5;
            		}
            		
            		if (typeElectronicComboBow.getValue().equals("Laptop")) {
            			facteurEmission = 0.15;
            			energie = 0.3;
            		}
            		
            		if (typeElectronicComboBow.getValue().equals("TV LCD")) {
            			facteurEmission = 0.1;
            			energie = 0.1;
            		}
            		
            		if (typeElectronicComboBow.getValue().equals("Smartphone")) {
            			facteurEmission = 0.02;
            			energie = 0.5;
            		}
            		
            		if (typeElectronicComboBow.getValue().equals("Consol")) {
            			facteurEmission = 0.1;
            			energie = 0.15;
            		}
            		
            		if (typeElectronicComboBow.getValue().equals("Air Conditioner")) {
            			facteurEmission = 0.3;
            			energie = 0.25;
            		}
            		
            		Double resultPerDay = dureeUtulisation * facteurEmission * energie;
            		Double resultPerYear = resultPerDay * 365;
            		
            		footPrintPerDayField.setText(String.format("%.2f", resultPerDay).toString() + " (G)");
            		footPrintPerYearField.setText(String.format("%.2f", resultPerYear).toString() + " (G)");
        		}
        		
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
    	global.back("activities.fxml", backBtn);
    }



}

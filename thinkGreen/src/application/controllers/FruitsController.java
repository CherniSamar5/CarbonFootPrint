package application.controllers;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class FruitsController implements Initializable {

    @FXML
    private ComboBox<String> typeFruitsComboBox;


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
    private double facteurCorrection = 1.1;

    public void prepareSolutions() {
    	solutions.add("Choose locally grown fruits: Opt for locally sourced fruits to reduce transportation emissions associated with long-distance shipping.");
    	solutions.add("Support organic farming practices: Purchase organic fruits that are grown without the use of synthetic pesticides and fertilizers, which helps to minimize environmental impact.");
    	solutions.add("Reduce food waste: Minimize fruit waste by purchasing only what you need and properly storing and preserving fruits to extend their shelf life.");
    	solutions.add("Compost fruit scraps: Instead of throwing away fruit peels and scraps, consider composting them to reduce methane emissions in landfills and create nutrient-rich compost for your garden.");
    	solutions.add("Choose seasonal fruits: Select fruits that are in season, as they require less energy-intensive production methods compared to out-of-season fruits that may need to be imported.");
    	solutions.add("Use alternative transportation methods: When possible, choose sustainable transportation options such as walking, cycling, or public transportation when purchasing fruits from local markets.");
    	solutions.add("Support sustainable packaging: Look for fruits that are packaged in eco-friendly materials or choose unpackaged fruits to minimize packaging waste.");
    	solutions.add("Practice mindful consumption: Consume fruits in moderation and avoid excessive food waste. Plan meals and snacks to ensure that fruits are fully utilized.");
    	solutions.add("Consider backyard or community gardening: Grow your own fruits in a backyard garden or participate in a community garden to reduce the carbon footprint associated with commercial fruit production and transportation.");
    	solutions.add("Educate and spread awareness: Raise awareness about the environmental impact of fruit production and consumption, encouraging others to make sustainable choices.");
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		prepareSolutions();
		typeFruitsComboBox.setItems(FXCollections.observableArrayList(
				"Orange",
				"Apple",
				"Banana",
				"Strawberry"
			)
		);
		
		typeFruitsComboBox.setValue(typeFruitsComboBox.getItems().get(0));
		
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
        		if (typeFruitsComboBox.getValue().equals("Orange")) {
        			facteurEmission = 0.16;
        		}
        		
        		if (typeFruitsComboBox.getValue().equals("Apple")) {
        			facteurEmission = 0.15;
        		}
        		
        		if (typeFruitsComboBox.getValue().equals("Banana")) {
        			facteurEmission = 0.19;
        		}
        		
        		if (typeFruitsComboBox.getValue().equals("Strawberry")) {
        			facteurEmission = 0.35;
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

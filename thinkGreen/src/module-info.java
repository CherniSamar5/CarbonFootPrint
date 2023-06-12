module thinkGreen {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
    exports application.controllers;
    opens application.controllers to javafx.fxml;
	opens application to javafx.graphics, javafx.fxml;
}

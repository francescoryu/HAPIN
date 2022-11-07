package ch.francescoryu.hapin.components.boxes;

import ch.francescoryu.hapin.buttonMethods.MenuMethods;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class WelcomeBox extends VBox {
    public WelcomeBox() {
        Label welcomeLabel = new Label("Welcome!");
        MenuMethods.setLabelStyle(welcomeLabel);
        VBox textBox = new VBox();
        textBox.setAlignment(Pos.CENTER);
        textBox.getChildren().addAll(welcomeLabel);

        getChildren().addAll(textBox);
        setStyle("-fx-padding: 20");
        setAlignment(Pos.TOP_CENTER);
    }
}

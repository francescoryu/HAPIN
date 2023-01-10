package ch.francescoryu.hapin.components.boxes;

import ch.francescoryu.hapin.backend.DataHandler;
import ch.francescoryu.hapin.buttonMethods.MenuMethods;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class WelcomeBox extends VBox {
    public WelcomeBox() {
        Label welcomeLabel = new Label("Welcome " + DataHandler.transferUserName() + "!");
        Label currentDateLabel = new Label("Current Date: " + DataHandler.getLocalDate());

        setLabelStyle(welcomeLabel);
        setLabelStyle(currentDateLabel);

        VBox textBox = new VBox();
        textBox.setAlignment(Pos.CENTER);
        textBox.getChildren().addAll(welcomeLabel, DataHandler.createClock(), currentDateLabel);

        getChildren().addAll(textBox);
        setStyle("-fx-padding: 20");
        setAlignment(Pos.TOP_CENTER);
    }

    public static void setLabelStyle(Node node) {
        node.setStyle("-fx-text-fill: black; -fx-font-size: 50");
    }
}

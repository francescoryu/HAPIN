package ch.francescoryu.hapin.popups;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;

public class ErrorPopup extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Label label = new Label("You have to fill out the textfields!!!");

        Button button = new Button();
        button.setText("Got it!");

        button.setOnAction(actionEvent -> {
            stage.hide();
        });

        VBox vBox = new VBox();
        vBox.setStyle("-fx-alignment: center");
        vBox.getChildren().addAll(label, button);
        vBox.setSpacing(20);
        vBox.setStyle("-fx-background-image: url(loginBackground.png);" +
                "-fx-background-position: center center;" +
                "-fx-alignment: center;" +
                "-fx-font-size: 25;" +
                "-fx-font-family: 'Microsoft Sans Serif'");


        Scene scene = new Scene(vBox, 400, 200);
        stage.setScene(scene);
        stage.setTitle("Error");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }
}

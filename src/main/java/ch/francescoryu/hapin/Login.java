package ch.francescoryu.hapin;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Login extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Label loginLabel = new Label("Login");
        loginLabel.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 30");

        HBox loginLabelBox = new HBox(loginLabel);
        loginLabelBox.setAlignment(Pos.TOP_CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(loginLabelBox);

        Scene scene = new Scene(borderPane, 400, 200);
        stage.setScene(scene);
        stage.show();

    }
}

package ch.francescoryu.hapin;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Login extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Label loginLabel = new Label("Login");
        loginLabel.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 30");

        HBox loginLabelBox = new HBox(loginLabel);
        loginLabelBox.setAlignment(Pos.TOP_CENTER);

        ComboBox<String> userComboBox = new ComboBox<>();
        DataHandler.loadUserToComboBox(userComboBox);
        userComboBox.setMinWidth(200);
        userComboBox.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 15");

        TextField pwdTextField = new TextField();
        pwdTextField.setMaxWidth(200);

        VBox inputBox = new VBox();
        inputBox.getChildren().addAll(userComboBox, pwdTextField);
        inputBox.setSpacing(30);
        inputBox.setStyle("-fx-padding: 10");
        inputBox.setAlignment(Pos.TOP_CENTER);

        Button loginButton = new Button("Login");
        loginButton.setOnAction(actionEvent -> {
            try {
                DataHandler.checkLoginData(userComboBox.getValue(), pwdTextField);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        HBox buttonBox = new HBox(loginButton);
        buttonBox.setAlignment(Pos.TOP_CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(loginLabelBox);
        borderPane.setCenter(inputBox);
        borderPane.setBottom(buttonBox);

        Scene scene = new Scene(borderPane, 400, 200);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }
}
